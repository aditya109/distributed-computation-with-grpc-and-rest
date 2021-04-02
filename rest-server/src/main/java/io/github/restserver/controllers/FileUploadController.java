package io.github.restserver.controllers;

import io.github.restserver.helper.FileUploader;
import io.github.restserver.helper.MatrixLoader;
import io.github.restserver.helper.PathProvider;
import io.github.restserver.models.Status;
import io.github.restserver.models.UploadFileResponse;
import io.github.restserver.services.ComputeService;
import io.github.restserver.middleware.LoggerProvider;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@RestController
public class FileUploadController {
    private Logger logger;
    private final FileUploader fileUploader;
    private final MatrixLoader matrixLoader;
    private final PathProvider pathProvider;
    private final ComputeService computeService;
    private final ThreadedComputeController threadedComputeController;
    private final GrpcServerScalingController grpcServerScalingController;

    public FileUploadController(FileUploader fileUploader, MatrixLoader matrixLoader, PathProvider pathProvider, ComputeService computeService, ThreadedComputeController threadedComputeController, GrpcServerScalingController grpcServerScalingController) {
        this.fileUploader = fileUploader;
        this.matrixLoader = matrixLoader;
        this.pathProvider = pathProvider;
        this.computeService = computeService;
        this.threadedComputeController = threadedComputeController;
        this.grpcServerScalingController = grpcServerScalingController;
        this.logger = new LoggerProvider(FileUploadController.class).provideLoggerInstance();
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2) {

        try {
            if (file1.isEmpty() || file2.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Files are empty");
            }
            if (fileUploader.uploadFile(file1) && fileUploader.uploadFile(file2)) {
                // trigger file-to-matrix transformation
                String storagePath = pathProvider.provideStoragePath();
                ArrayList<ArrayList<Long>> matrixA = matrixLoader.loadFileDataOntoMatrix(storagePath + file1.getOriginalFilename());
                logger.info("=================================");
                logger.info("matrix A : ");
                logger.info("=================================");
                computeService.displayMatrix(matrixA);
                logger.info("=================================");
                ArrayList<ArrayList<Long>> matrixB = matrixLoader.loadFileDataOntoMatrix(storagePath + file2.getOriginalFilename());
                logger.info("initiating matrix transpose.... ");
                matrixB = computeService.returnTranspose(matrixB);
                logger.info("matrix B now : ");
                logger.info("=================================");
                computeService.displayMatrix(matrixB);
                if (computeService.isMultiplicationPossible(matrixA, matrixB)) {
                    ArrayList<ArrayList<Long>> matrixC = threadedComputeController.run(matrixA, matrixB);
                    UploadFileResponse uploadFileResponse = new UploadFileResponse();
                    uploadFileResponse.setMultiplicationResult(matrixC);
                    uploadFileResponse.setStatus(Status.SUCCESS);

                    // move ahead and trigger asynchronous row-column multiplications
                    logger.info("=================================");
                    logger.info("printing the resultant matrix....");
                    computeService.displayMatrix(matrixC);
                    logger.info("=================================");
                    return ResponseEntity.ok(uploadFileResponse);
                } else {
                    // throw error response
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("provided matrices can't be multiplied\nsize do not match\n, OR, size not in powers of 2");
                }
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong while uploading files");
            }
        } catch (Exception e) {
            logger.error("error occurred within business logic of /upload endpoint");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }

    @PostMapping("/kill-kids")
    public ResponseEntity<?> killChildren() {
        grpcServerScalingController.grpcServerScaleDown();
        return ResponseEntity.ok(grpcServerScalingController.getPortList().size() + " gRPC workers were killed");
    }
}
