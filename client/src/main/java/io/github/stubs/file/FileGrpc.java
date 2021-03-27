package io.github.stubs.file;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: file.proto")
public final class FileGrpc {

  private FileGrpc() {}

  public static final String SERVICE_NAME = "File";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.github.stubs.file.FileOuterClass.UploadFileRequest,
      io.github.stubs.file.FileOuterClass.UploadFileResponse> getUploadImageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UploadImage",
      requestType = io.github.stubs.file.FileOuterClass.UploadFileRequest.class,
      responseType = io.github.stubs.file.FileOuterClass.UploadFileResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<io.github.stubs.file.FileOuterClass.UploadFileRequest,
      io.github.stubs.file.FileOuterClass.UploadFileResponse> getUploadImageMethod() {
    io.grpc.MethodDescriptor<io.github.stubs.file.FileOuterClass.UploadFileRequest, io.github.stubs.file.FileOuterClass.UploadFileResponse> getUploadImageMethod;
    if ((getUploadImageMethod = FileGrpc.getUploadImageMethod) == null) {
      synchronized (FileGrpc.class) {
        if ((getUploadImageMethod = FileGrpc.getUploadImageMethod) == null) {
          FileGrpc.getUploadImageMethod = getUploadImageMethod = 
              io.grpc.MethodDescriptor.<io.github.stubs.file.FileOuterClass.UploadFileRequest, io.github.stubs.file.FileOuterClass.UploadFileResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "File", "UploadImage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.stubs.file.FileOuterClass.UploadFileRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.stubs.file.FileOuterClass.UploadFileResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FileMethodDescriptorSupplier("UploadImage"))
                  .build();
          }
        }
     }
     return getUploadImageMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FileStub newStub(io.grpc.Channel channel) {
    return new FileStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FileBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new FileBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FileFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new FileFutureStub(channel);
  }

  /**
   */
  public static abstract class FileImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<io.github.stubs.file.FileOuterClass.UploadFileRequest> uploadImage(
        io.grpc.stub.StreamObserver<io.github.stubs.file.FileOuterClass.UploadFileResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getUploadImageMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getUploadImageMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                io.github.stubs.file.FileOuterClass.UploadFileRequest,
                io.github.stubs.file.FileOuterClass.UploadFileResponse>(
                  this, METHODID_UPLOAD_IMAGE)))
          .build();
    }
  }

  /**
   */
  public static final class FileStub extends io.grpc.stub.AbstractStub<FileStub> {
    private FileStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FileStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FileStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<io.github.stubs.file.FileOuterClass.UploadFileRequest> uploadImage(
        io.grpc.stub.StreamObserver<io.github.stubs.file.FileOuterClass.UploadFileResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getUploadImageMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class FileBlockingStub extends io.grpc.stub.AbstractStub<FileBlockingStub> {
    private FileBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FileBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FileBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class FileFutureStub extends io.grpc.stub.AbstractStub<FileFutureStub> {
    private FileFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FileFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FileFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_UPLOAD_IMAGE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FileImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FileImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPLOAD_IMAGE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.uploadImage(
              (io.grpc.stub.StreamObserver<io.github.stubs.file.FileOuterClass.UploadFileResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class FileBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FileBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.github.stubs.file.FileOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("File");
    }
  }

  private static final class FileFileDescriptorSupplier
      extends FileBaseDescriptorSupplier {
    FileFileDescriptorSupplier() {}
  }

  private static final class FileMethodDescriptorSupplier
      extends FileBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FileMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FileGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FileFileDescriptorSupplier())
              .addMethod(getUploadImageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
