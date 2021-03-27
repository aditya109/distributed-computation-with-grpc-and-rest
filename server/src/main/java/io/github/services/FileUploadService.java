package io.github.services;

import com.google.protobuf.ByteString;
import io.github.stubs.file.FileGrpc;
import io.github.stubs.file.FileOuterClass;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FileUploadService extends FileGrpc.FileImplBase {
    private DiskFileStore diskFileStore;

    public FileUploadService() {
        this.diskFileStore = new DiskFileStore("img");
    }

    @Override
    public StreamObserver<FileOuterClass.UploadFileRequest> uploadImage(StreamObserver<FileOuterClass.UploadFileResponse> responseObserver) {
        return new StreamObserver<FileOuterClass.UploadFileRequest>() {

            private String fileId;
            private String fileType;
            private ByteArrayOutputStream fileData;

            @Override
            public void onNext(FileOuterClass.UploadFileRequest uploadFileRequest) {
                if (uploadFileRequest.getDataCase() == FileOuterClass.UploadFileRequest.DataCase.INFO) {
                    FileOuterClass.ImageInfo info = uploadFileRequest.getInfo();
                    System.out.println("receive file info: \n" + info);

                    fileId = info.getFileId();
                    fileType = info.getFileType();
                    fileData = new ByteArrayOutputStream();

                    return;
                }
                ByteString chunkData = uploadFileRequest.getChunkData();
                System.out.println("receive file chunk with size: \n" + chunkData.size());

                if (fileData == null) {
                    System.out.println("client did not send data before");
                    responseObserver.onError(
                            Status.INVALID_ARGUMENT
                            .withDescription("file info wasn't sent before")
                            .asRuntimeException()
                    );
                    return;
                }
                try {
                    chunkData.writeTo(fileData);
                } catch (IOException e) {
                    responseObserver.onError(
                            Status.INTERNAL
                                    .withDescription("cannot write chunk data: " + e.getMessage())
                                    .asRuntimeException()
                    );
                    return;
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                String id = "";
                int fileSize = fileData.size();

                try {
                    diskFileStore.save(fileId, fileType, fileData);
                }
                catch (IOException e) {
                    responseObserver.onError(
                            Status.INTERNAL
                                    .withDescription("cannot write chunk data: " + e.getMessage())
                                    .asRuntimeException()
                    );
                    return;
                }
                FileOuterClass.UploadFileResponse response = FileOuterClass.UploadFileResponse.newBuilder()
                        .setId(id)
                        .setSize(fileSize)
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }
}
