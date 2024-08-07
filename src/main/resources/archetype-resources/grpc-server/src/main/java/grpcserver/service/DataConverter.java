#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.grpcserver.service;

import ${package}.baseservice.model.Example;
import ${package}.grpcdataservice.ServiceData;

public class DataConverter {
    public static ServiceData convertToProto(Example template) {
        return ServiceData.newBuilder()
                .setId(template.getId())
                .setText(template.getText())
                .build();
    }

    public static Example convertToPojo(ServiceData templateData) {
        return new Example(
                templateData.getId(),
                templateData.getText()
        );
    }
}
