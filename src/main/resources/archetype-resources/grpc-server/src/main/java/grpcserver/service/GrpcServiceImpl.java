#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.grpcserver.service;

import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import ${package}.grpcdataservice.DataServiceGrpc;
import ${package}.grpcdataservice.RequestById;
import ${package}.grpcdataservice.RequestCreate;
import ${package}.grpcdataservice.Response;
import ${package}.model.BaseData;
import ${package}.baseservice.model.Example;
import ${package}.baseservice.service.DataService;

@GrpcService
@Slf4j
public class GrpcServiceImpl extends DataServiceGrpc.DataServiceImplBase {
    private final DataService dataService;

    public GrpcServiceImpl(DataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public void findData(RequestById request,
                             StreamObserver<Response> responseObserver) {
        Long id = request.getId();
        log.info("Finding template by id: {}", id);

        try {
            var template = dataService.findDataById(id);
            Response response = Response.newBuilder()
                    .setResponseData(DataConverter.convertToProto(template))
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(
                    Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException()
            );
        }

    }

    @Override
    public void findAll(Empty request,
                        StreamObserver<Response> responseObserver) {
        try {
            var templates = dataService.findAllData();
            for (BaseData data : templates) {
                responseObserver.onNext(Response.newBuilder()
                        .setResponseData(DataConverter.convertToProto((Example)data)).build());
            }
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(
                    Status.NOT_FOUND.withDescription(e.getMessage()).asRuntimeException()
            );
        }
    }

    @Override
    public void createData(RequestCreate request,
                               StreamObserver<Response> responseObserver) {

        var createdTemplate = dataService.createData(
                new BaseData(
                        request.getId(),
                        request.getText()));
        responseObserver.onNext(Response.newBuilder().setResponseData(
                DataConverter.convertToProto(createdTemplate)).build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateData(RequestCreate request,
                               StreamObserver<Response> responseObserver) {
        var createdTemplate = dataService.updateData(
                new BaseData(
                        request.getId(),
                        request.getText()));
        responseObserver.onNext(Response.newBuilder().setResponseData(
                DataConverter.convertToProto(createdTemplate)).build());
        responseObserver.onCompleted();
    }

    @Override
    public void delete(RequestById request,
                       StreamObserver<Empty> responseObserver) {
        var id = request.getId();
        dataService.deleteById(id);
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }
}
