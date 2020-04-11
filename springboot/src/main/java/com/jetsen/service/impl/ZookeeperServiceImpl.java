package com.jetsen.service.impl;

import com.jetsen.configuration.ZookeeperConfig;
import com.jetsen.service.ZookeeperService;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collector;

/**
 * @author NICKEL
 */
@Service
@Slf4j
public class ZookeeperServiceImpl implements ZookeeperService {
    @Autowired
    private CuratorFramework client;

    @Override
    public void create(String path, byte[] payload) throws Exception {
        try {
//            client.create().creatingParentsIfNeeded().forPath(path, payload);
            List<ACL> acls = new ArrayList<ACL>();
            acls.add(new ACL(ZooDefs.Perms.ALL, new Id("digest", "nickel:ironpearl")));
            client.create().creatingParentsIfNeeded().withACL(acls).forPath(path, payload);
        } catch (Exception e) {
            log.error("{}节点创建失败:{}", path, e);
            throw e;
        }
    }

    @Override
    public void createEphemeral(String path, byte[] payload) throws Exception {
        try {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, payload);
        } catch (Exception e) {
            log.error("{}临时节点创建失败:{}", path, e);
            throw e;
        }
    }

    @Override
    public Collection<CuratorTransactionResult> transaction() throws Exception {
        try {
            CuratorOp createOp = client.transactionOp().create().forPath("/a", "some data".getBytes());
            CuratorOp deleteOp = client.transactionOp().delete().forPath("/test");
            //以下方法会失败，测试整个会回滚
            CuratorOp createOp2 = client.transactionOp().create().forPath("/a1", "some data".getBytes());
            Collection<CuratorTransactionResult> results = client.transaction().forOperations(createOp, deleteOp, createOp2);
            for (CuratorTransactionResult result : results) {
                log.info(result.getForPath() + " - " + result.getType());
            }

            return results;
        } catch (Exception e) {
            log.error("处理失败，事务回滚:{}", e);
            throw e;
        }
    }

    @Override
    public void createEphemeralSequential(String path, byte[] payload) throws Exception {
        try {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, payload);
        } catch (Exception e) {
            log.error("{}临时节点创建失败:{}", path, e);
            throw e;
        }
    }

    @Override
    public void setData(String path, byte[] payload) {

    }

    @Override
    public void setDataAsync(String path, byte[] payload) {

    }

    @Override
    public void delete(String path) throws Exception {
        try {
            client.delete().forPath(path);
        } catch (Exception e) {
            log.error("{}节点创建失败:{}", path, e);
            throw new Exception(e);
        }
    }

    @Override
    public void guaranteedDelete(String path) throws Exception {
        try {
            client.delete().guaranteed().forPath(path);
        } catch (Exception e) {
            log.error("{}节点创建失败:{}", path, e);
            throw e;
        }
    }

    @Override
    public void watchNode(String path) throws Exception {
        try {
//            client.getChildren()
//                    .usingWatcher((CuratorWatcher) event -> log.info("触发watcher，节点路径为:" + event.getPath()))
//                    .forPath(path);

            //删除监听事件可以使用 nodeCache.getListenable().removeListener()
            NodeCache nodeCache = new NodeCache(client, path);
            nodeCache.start();
            nodeCache.getListenable().addListener(() -> {
                if (nodeCache.getCurrentData() == null) {
                    log.info("{}为空", path);
                    return;
                }
                String data = new String(nodeCache.getCurrentData().getData());
                log.info("节点路径{}的值为{}", path, data);
            });
        } catch (Exception e) {
            log.error("", path, e);
            throw e;
        }
    }

    @Override
    public void watchNodeChildren(String path) {
        try {
            PathChildrenCache childrenCache = new PathChildrenCache(client, path, true);
            childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
            childrenCache.getListenable().addListener((client, event) -> {
                switch (event.getType()) {
                    //PathChildrenCacheEvent.Type.INITIALIZED
                    case INITIALIZED:
                        log.info("子节点初始化 ok...");
                        break;
                    case CHILD_ADDED:
                        log.info("添加子节点{},内容为{}", event.getData().getPath(), new String(event.getData().getData()));
                        break;
                    case CHILD_REMOVED:
                        log.info("删除子节点{}", event.getData().getPath());
                        break;
                    case CHILD_UPDATED:
                        log.info("更新子节点{}", new String(event.getData().getData()));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDataAsyncWithCallback(BackgroundCallback callback, String path, byte[] payload) {

    }

    /*
     * InterProcessMutex ： 可重入的互斥锁， 同一线程调用acquire几次，就要release几次
     * InterProcessSemaphoreMutex : 不可重入的互斥锁
     * InterProcessReadWriteLock ： 读写锁
     *   InterProcessReadWriteLock lock = new InterProcessReadWriteLock(client, lockPath);
     *   InterProcessMutex readLock = lock.readLock();
     *   InterProcessMutex writeLock = lock.writeLock();
     * new InterProcessSemaphoreV2(client, lockPath, 1) 共享信号量，实现公平锁
     * */
    @Override
    public void testDistributedLock(String path) {
//        Executor executor = Executors.newFixedThreadPool(10);
        Executor executor = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        }, new ThreadPoolExecutor.DiscardOldestPolicy());

        InterProcessReadWriteLock lock =new InterProcessReadWriteLock(client,path+"/lock_");
        InterProcessLock readLock = lock.readLock();

        for (int i = 0; i < 13; i++) {
            executor.execute(() -> {
//                InterProcessMutex lock = new InterProcessMutex(client, path + "/lock_");

                try {
                    //acquire the mutex -blocking until it's available
                    readLock.acquire();
                    log.info("{}线程获得分布式锁", Thread.currentThread().getName());
                    Thread.sleep(5000); //休息5秒，模拟业务执行逻辑
                    readLock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
