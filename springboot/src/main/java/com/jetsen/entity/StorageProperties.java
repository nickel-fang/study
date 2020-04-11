package com.jetsen.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author NICKEL
 */
@Component
@Data
//@ConfigurationProperties("storage")
public class StorageProperties {
    private String location = "upload-dir";
}
