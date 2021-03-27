package io.github.stubs.matrix;

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
    comments = "Source: matrix.proto")
public final class computeGrpc {

  private computeGrpc() {}

  public static final String SERVICE_NAME = "compute";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.github.stubs.matrix.Matrix.multiplyBlockRequest,
      io.github.stubs.matrix.Matrix.multiplyBlockResponse> getMultiplyRowByColumnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "multiplyRowByColumn",
      requestType = io.github.stubs.matrix.Matrix.multiplyBlockRequest.class,
      responseType = io.github.stubs.matrix.Matrix.multiplyBlockResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.stubs.matrix.Matrix.multiplyBlockRequest,
      io.github.stubs.matrix.Matrix.multiplyBlockResponse> getMultiplyRowByColumnMethod() {
    io.grpc.MethodDescriptor<io.github.stubs.matrix.Matrix.multiplyBlockRequest, io.github.stubs.matrix.Matrix.multiplyBlockResponse> getMultiplyRowByColumnMethod;
    if ((getMultiplyRowByColumnMethod = computeGrpc.getMultiplyRowByColumnMethod) == null) {
      synchronized (computeGrpc.class) {
        if ((getMultiplyRowByColumnMethod = computeGrpc.getMultiplyRowByColumnMethod) == null) {
          computeGrpc.getMultiplyRowByColumnMethod = getMultiplyRowByColumnMethod = 
              io.grpc.MethodDescriptor.<io.github.stubs.matrix.Matrix.multiplyBlockRequest, io.github.stubs.matrix.Matrix.multiplyBlockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "compute", "multiplyRowByColumn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.stubs.matrix.Matrix.multiplyBlockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.stubs.matrix.Matrix.multiplyBlockResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new computeMethodDescriptorSupplier("multiplyRowByColumn"))
                  .build();
          }
        }
     }
     return getMultiplyRowByColumnMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static computeStub newStub(io.grpc.Channel channel) {
    return new computeStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static computeBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new computeBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static computeFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new computeFutureStub(channel);
  }

  /**
   */
  public static abstract class computeImplBase implements io.grpc.BindableService {

    /**
     */
    public void multiplyRowByColumn(io.github.stubs.matrix.Matrix.multiplyBlockRequest request,
        io.grpc.stub.StreamObserver<io.github.stubs.matrix.Matrix.multiplyBlockResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getMultiplyRowByColumnMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMultiplyRowByColumnMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                io.github.stubs.matrix.Matrix.multiplyBlockRequest,
                io.github.stubs.matrix.Matrix.multiplyBlockResponse>(
                  this, METHODID_MULTIPLY_ROW_BY_COLUMN)))
          .build();
    }
  }

  /**
   */
  public static final class computeStub extends io.grpc.stub.AbstractStub<computeStub> {
    private computeStub(io.grpc.Channel channel) {
      super(channel);
    }

    private computeStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected computeStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new computeStub(channel, callOptions);
    }

    /**
     */
    public void multiplyRowByColumn(io.github.stubs.matrix.Matrix.multiplyBlockRequest request,
        io.grpc.stub.StreamObserver<io.github.stubs.matrix.Matrix.multiplyBlockResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMultiplyRowByColumnMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class computeBlockingStub extends io.grpc.stub.AbstractStub<computeBlockingStub> {
    private computeBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private computeBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected computeBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new computeBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.github.stubs.matrix.Matrix.multiplyBlockResponse multiplyRowByColumn(io.github.stubs.matrix.Matrix.multiplyBlockRequest request) {
      return blockingUnaryCall(
          getChannel(), getMultiplyRowByColumnMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class computeFutureStub extends io.grpc.stub.AbstractStub<computeFutureStub> {
    private computeFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private computeFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected computeFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new computeFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.stubs.matrix.Matrix.multiplyBlockResponse> multiplyRowByColumn(
        io.github.stubs.matrix.Matrix.multiplyBlockRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getMultiplyRowByColumnMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MULTIPLY_ROW_BY_COLUMN = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final computeImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(computeImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MULTIPLY_ROW_BY_COLUMN:
          serviceImpl.multiplyRowByColumn((io.github.stubs.matrix.Matrix.multiplyBlockRequest) request,
              (io.grpc.stub.StreamObserver<io.github.stubs.matrix.Matrix.multiplyBlockResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class computeBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    computeBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.github.stubs.matrix.Matrix.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("compute");
    }
  }

  private static final class computeFileDescriptorSupplier
      extends computeBaseDescriptorSupplier {
    computeFileDescriptorSupplier() {}
  }

  private static final class computeMethodDescriptorSupplier
      extends computeBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    computeMethodDescriptorSupplier(String methodName) {
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
      synchronized (computeGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new computeFileDescriptorSupplier())
              .addMethod(getMultiplyRowByColumnMethod())
              .build();
        }
      }
    }
    return result;
  }
}
