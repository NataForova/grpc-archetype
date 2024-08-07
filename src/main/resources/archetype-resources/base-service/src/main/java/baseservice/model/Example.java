#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.baseservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import ${package}.model.BaseData;

@Entity
@Table(name = "example")
public class Example extends BaseData {
    public Example(Long id, String name) {
        super(id, name);
    }
    public Example() {

    }
}
