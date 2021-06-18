package com.tekmentor.jmsvirtualization.extensions;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SimplifiedMessageAttribute {
    final String name;
    final String value;
    final String dataType;

    public SimplifiedMessageAttribute(String name, String value, String dataType) {
        this.name = name;
        this.value = value;
        this.dataType = dataType;
    }


}
