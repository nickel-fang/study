package com.jetsen.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author NICKEL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Value {
    private Long id;
    @JsonProperty(value = "quote")
    private String content;
}
