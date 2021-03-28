package io.github.restserver.controllers;

import io.github.restserver.helper.FileUploadHelper;
import io.github.restserver.helper.MatrixLoaderHelper;
import io.github.restserver.helper.PathProvider;
import io.github.restserver.models.Status;
import io.github.restserver.models.UploadFileResponse;
import io.github.restserver.services.ComputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class FileUploadController {
    @Autowired
    private FileUploadHelper fileUploadHelper;

    @Autowired
    private MatrixLoaderHelper matrixLoaderHelper;

    @Autowired
    private PathProvider pathProvider;

    @Autowired
    private ComputeService computeService;

    @Autowired
    private ThreadedComputeController threadedComputeController;

    @Autowired
    private GrpcServerScalingController grpcServerScalingController;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2) {

        try {
            if (file1.isEmpty() || file2.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Files are empty");
            }
            if (fileUploadHelper.uploadFile(file1) && fileUploadHelper.uploadFile(file2)) {
                // trigger file-to-matrix transformation
                String storagePath = pathProvider.provideStoragePath();
                ArrayList<ArrayList<Long>> matrixA = matrixLoaderHelper.loadFileDataOntoMatrix(storagePath + file1.getOriginalFilename());
                System.out.println("=================================");
                System.out.println("Matrix A : ");
                System.out.println("=================================");
                computeService.displayMatrix(matrixA);
                System.out.println("=================================");
                ArrayList<ArrayList<Long>> matrixB = matrixLoaderHelper.loadFileDataOntoMatrix(storagePath + file2.getOriginalFilename());
                System.out.println("Initiating matrix transpose.... ");
                matrixB = computeService.returnTranspose(matrixB);
                System.out.println("Matrix B now : ");
                System.out.println("=================================");
                computeService.displayMatrix(matrixB);
                if (computeService.isMultiplicationPossible(matrixA, matrixB)) {
                    ArrayList<ArrayList<Long>> matrixC = threadedComputeController.run(matrixA, matrixB);
                    UploadFileResponse uploadFileResponse = new UploadFileResponse();
                    uploadFileResponse.setMultiplicationResult(matrixC);
                    uploadFileResponse.setStatus(Status.SUCCESS);

                    // move ahead and trigger asynchronous row-column multiplications
                    System.out.println("=================================");
                    System.out.println("Printing the resultant matrix....");
                    computeService.displayMatrix(matrixC);
                    System.out.println("=================================");
                    return ResponseEntity.ok(uploadFileResponse);
                } else {
                    // throw error response
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("provided matrices can't be multiplied\nsize do not match\n, OR, size not in powers of 2");
                }
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong while uploading files");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
    }

    @PostMapping("/kill-kids")
    public ResponseEntity<?> killChildren() {
        try {
            grpcServerScalingController.grpcServerScaleDown();
            return ResponseEntity.ok(grpcServerScalingController.getPortList().size() + " gRPC workers were killed");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("workers couldn't be killed.\n Please restart the server.");
    }
}
