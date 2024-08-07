#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.grpcclient.client;

import ${package}.communication.GrpcCommunicationInterface;
import ${package}.exception.ResourceNotFoundException;
import ${package}.grpcdataservice.DataServiceGrpc;

import ${package}.grpcdataservice.RequestById;
import ${package}.model.BaseData;

import org.springframework.stereotype.Service;

import java.util.List;

import static ${package}.grpcserver.service.DataConverter.convertToPojo;

@Service
public class GrpcClient implements GrpcCommunicationInterface {
    @net.devh.boot.grpc.client.inject.GrpcClient("grpc-server")
    private DataServiceGrpc.DataServiceBlockingStub templateServiceStub;

    @Override
    public BaseData findDataById(Long id) throws ResourceNotFoundException {
        RequestById request = RequestById.newBuilder().setId(id)
                .build();
        var response = this.templateServiceStub.findData(request);
        return convertToPojo(response.getResponseData());
    }

    @Override
    public List<BaseData> findAllData() {
        return List.of();
    }

    @Override
    public BaseData createData(BaseData baseData) {
        return null;
    }

    @Override
    public BaseData updateData(BaseData baseData) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {

    }
}
