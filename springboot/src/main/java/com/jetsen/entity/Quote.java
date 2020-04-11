package com.jetsen.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author NICKEL
 */
//@JsonIgnoreProperties from the Jackson JSON processing library to indicate
//that any properties not bound in this type should be ignored
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Quote {
    private String type;
    private Value value;
}
