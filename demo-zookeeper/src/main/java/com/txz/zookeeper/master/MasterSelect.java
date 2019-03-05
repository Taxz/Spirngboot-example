package com.txz.zookeeper.master;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taxz on 2019/3/4/004.
 * GitHub:https://github.com/Taxz
 * master 选举
 */
public class MasterSelect {

    public static String path = "/master_select_path";

    public static void main(String[] args) throws InterruptedException {


    }

    private static void selector() throws InterruptedException {
        CuratorFramework curator = getClient();
        curator.start();
        LeaderSelector selector = new LeaderSelector(curator, path, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                System.out.println("I am master.");
                Thread.sleep(3000);
                System.out.println("释放master 权利");
            }
        });

        //保证在此实例释放领导权之后还可能获得领导权
        selector.autoRequeue();
        selector.start();
        Thread.sleep(10 * 1000);
    }

    private static void latch() {
        List<LeaderLatch> latches = new ArrayList<>();
        List<CuratorFramework> curators = new ArrayList<>();
        try {
            for (int i = 0; i < 10; i++) {
                CuratorFramework curator = getClient();
                curator.start();
                curators.add(curator);
                final String name = "client:" + i;
                final LeaderLatch leaderLatch = new LeaderLatch(curator, path, name);
                leaderLatch.addListener(new LeaderLatchListener() {
                    @Override
                    public void isLeader() {
                        System.out.println(leaderLatch.getId()+":I am leader , do something ....");
                    }

                    @Override
                    public void notLeader() {
                        System.out.println(leaderLatch.getId()+":I'm not leader,wait......");
                    }
                });
                leaderLatch.start();
            }
            Thread.sleep(1000 * 10);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            for (CuratorFramework client : curators) {
                CloseableUtils.closeQuietly(client);
            }

            for (LeaderLatch leaderLatch : latches) {
                CloseableUtils.closeQuietly(leaderLatch);
            }
        }
    }

    public static CuratorFramework getClient() {
        return CuratorFrameworkFactory.builder()
                .connectString("172.16.36.161:2181")
                .retryPolicy(new ExponentialBackoffRetry(1000,3)) //重试策略
                .connectionTimeoutMs(15*1000) //连接超时时间
                .sessionTimeoutMs(30*1000) //会话超时时间
                .namespace("Taxz")
                .build();
    }
}
