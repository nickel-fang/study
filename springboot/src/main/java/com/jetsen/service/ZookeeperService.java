package com.jetsen.service;

import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.zookeeper.Watcher;

import java.util.Collection;

public interface ZookeeperService {
    public void create(String path, byte[] payload) throws Exception;

    public void createEphemeral(String path, byte[] payload) throws Exception;

    Collection<CuratorTransactionResult> transaction() throws Exception;

    public void createEphemeralSequential(String path, byte[] payload) throws Exception;

    public void setData(String path, byte[] payload);

    public void setDataAsync(String path, byte[] payload);

    public void delete(String path) throws Exception;

    public void guaranteedDelete(String path) throws Exception;

    public void watchNode(String path) throws Exception;

    public void watchNodeChildren(String path);

    public void setDataAsyncWithCallback(BackgroundCallback callback, String path, byte[] payload);

    void testDistributedLock(String path);
}
