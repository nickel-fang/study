package com.jetsen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author NICKEL
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticEntity<T> {
    private long id;
    //json格式
    private String data;
}
