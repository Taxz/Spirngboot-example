package com.txz.zookeeper.lock;

import com.txz.zookeeper.master.MasterSelect;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;

import java.awt.font.TextHitInfo;

/**
 * Created by Taxz on 2019/3/5/005.
 * GitHub:https://github.com/Taxz
 */
public class RecipesBarrier {
    public static String path = "/barrier";
    public static DistributedBarrier barrier;

    public static void main(String[] args) throws Exception {

    }

    private void barrierPassive() throws Exception {
        for (int i = 0; i < 10; i++) {

            new Thread(() -> {
                try {
                    CuratorFramework client = MasterSelect.getClient();
                    client.start();
                    barrier = new DistributedBarrier(client, path);
                    barrier.setBarrier();
                    barrier.waitOnBarrier();
                    System.out.println("启动");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        }
        Thread.sleep(2000);
        barrier.removeBarrier();
    }

    private void barrierAuto() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    CuratorFramework client = MasterSelect.getClient();
                    client.start();
                    DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(client, path,10);
                    Thread.sleep(Math.round(Math.random() * 3000));
                    System.out.println(Thread.currentThread().getName() + "号进入barrier");
                    barrier.enter();
                    System.out.println("启动");
                    Thread.sleep(Math.round(Math.random() * 3000));
                    barrier.leave();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }
}
