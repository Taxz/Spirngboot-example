package com.txz.zookeeper.master;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by Taxz on 2019/3/4/004.
 * GitHub:https://github.com/Taxz
 * master 选举
 */
public class MasterSelect {

    public static String path = "/master_select_path";
    public static CuratorFramework curator = CuratorFrameworkFactory.builder()
            .connectString("172.16.36.161:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000,3)) //重试策略
            .connectionTimeoutMs(15*1000) //连接超时时间
            .sessionTimeoutMs(30*1000) //会话超时时间
            .namespace("Taxz")
            .build();

    public static void main(String[] args) throws InterruptedException {

        curator.start();
        LeaderSelector selector = new LeaderSelector(curator, path, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                System.out.println("I am master.");
                Thread.sleep(3000);
                System.out.println("释放master 权利");
            }
        });
        selector.autoRequeue();
        selector.start();
        Thread.sleep(10 * 1000);
    }
}
