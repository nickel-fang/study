package com.jetsen.configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

/**
 * @author NICKEL
 */
@Configuration
@Slf4j
@Data
@ConfigurationProperties(prefix = "apache.zookeeper")
public class ZookeeperConfig {
    private String host;
    private int maxRetry;
    private int sessionTimeout;
    private int connectionTimeout;
    private String namespace;
    private String auth;

    @Bean
    public CuratorFramework curatorFramework() {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .authorization("digest", auth.getBytes()).connectString(host)
                .sessionTimeoutMs(sessionTimeout).connectionTimeoutMs(connectionTimeout)
                .retryPolicy(new ExponentialBackoffRetry(1000, maxRetry))
                .namespace(namespace).build();
        client.start();
        return client;
    }

    @PreDestroy
    private void destroyClient() {
        curatorFramework().close();
        log.info("zookeeper client关闭成功");
    }
}
