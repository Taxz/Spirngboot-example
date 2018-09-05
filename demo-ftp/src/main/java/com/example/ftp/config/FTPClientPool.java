package com.example.ftp.config;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ftpClient连接池
 *
 * @author Taxz
 */

@Component
public class FTPClientPool {

    private static final int DEFAULT_POOL_SIZE = 10;
    @Value("${ftp.sys.path}")
    private String ftpSysPath;
    private BlockingQueue<FTPClient> pool;
    private FTPClientFactory factory;
    private FTPClientPool(){}

    public FTPClientPool(FTPClientFactory factory) throws Exception {
        this(DEFAULT_POOL_SIZE, factory);
    }

    public FTPClientPool(int poolSize, FTPClientFactory factory) throws Exception {
        this.factory = factory;
        this.pool = new ArrayBlockingQueue<>(poolSize);
        initPool(poolSize);
    }

    /**
     * 初始化连接池
     *
     * @param maxPoolSize 最大连接数
     * @throws Exception
     */
    private void initPool(int maxPoolSize) throws Exception {
        int count = 0;
        while (count < maxPoolSize) {
            pool.offer(factory.makeClient(), 3, TimeUnit.SECONDS);
            count++;
        }
    }

    /**
     * 获取ftpClient对象
     *
     * @return
     * @throws Exception
     * @throws NoSuchElementException
     * @throws IllegalStateException
     */
    public FTPClient borrowClient() throws Exception {
        FTPClient client = pool.take();
        if(client == null) {
            client = factory.makeClient();
            addClient(client);
        } else if(!factory.validateClient(client)) {
            invalidateClient(client);
            client = factory.makeClient();
            addClient(client);
        }

        return  client;
    }

    /**
     * 添加客户端
     *
     * @param ftpClient
     */
    private void addClient(FTPClient ftpClient){
        pool.offer(ftpClient);
    }

    /**
     * 归还一个对象，如果3秒内无法插入对象池，则销毁这个对象
     *
     * @param ftpClient
     * @throws Exception
     */
    public void returnClient(FTPClient ftpClient) throws Exception {
        if(ftpClient == null) return;
        if (!pool.offer(ftpClient,3,TimeUnit.SECONDS)) {
            try {
                System.out.println("******3"+ftpClient.printWorkingDirectory());
                factory.destroyClient(ftpClient);
                System.out.println("******4"+ftpClient.printWorkingDirectory());
            } catch (IOException e) {
                throw e;
            }
        }
        if(null!=ftpClient && ftpClient.isConnected()){
            ftpClient.logout();
        }
    }

    /**
     * 使客户端无效
     *
     * @param ftpClient
     * @throws Exception
     */
    public void invalidateClient(FTPClient ftpClient){
        pool.remove(ftpClient);
    }

    /**
     * 关闭连接池
     *
     * @throws Exception
     */
    public void close() throws Exception {
       while(pool.iterator().hasNext()){
            FTPClient client = pool.take();
            factory.destroyClient(client);
        }
    }

    public BlockingQueue<FTPClient> getPool() {
        return pool;
    }

    public FTPClientFactory getFactory() {
        return factory;
    }

}
