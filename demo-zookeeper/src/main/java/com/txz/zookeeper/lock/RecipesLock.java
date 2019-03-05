package com.txz.zookeeper.lock;

import com.txz.zookeeper.master.MasterSelect;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Taxz on 2019/3/5/005.
 * GitHub:https://github.com/Taxz
 */
public class RecipesLock {
    private static String path = "/lock";

    public static void main(String[] args) {
        CuratorFramework client = MasterSelect.getClient();
        client.start();
        final InterProcessMutex lock = new InterProcessMutex(client, path);
        final CountDownLatch downLatch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    downLatch.await();
                    lock.acquire();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    System.out.println("生成订单号："+orderNo);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    try {
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        downLatch.countDown();
    }
}
