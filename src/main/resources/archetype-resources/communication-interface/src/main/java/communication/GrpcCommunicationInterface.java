#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.communication;

import ${package}.exception.ResourceNotFoundException;
import ${package}.model.BaseData;

import java.util.List;

public interface GrpcCommunicationInterface {

    BaseData findDataById(Long id) throws ResourceNotFoundException;
    List<BaseData> findAllData();
    BaseData createData(BaseData baseData);
    BaseData updateData(BaseData baseData) throws ResourceNotFoundException;
    void deleteById(Long id) throws ResourceNotFoundException;

}
