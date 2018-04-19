package edu.example.study.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Taxz on 2018/4/18.
 */
public class DynamicDataSourceContextHolder {
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);
    private static final ThreadLocal<String> CONTEXT_HOLD = ThreadLocal.withInitial(DataSourceKeys.master::name);
    public static List<Object> lsit = new ArrayList<>();
    public static List<Object> slaveNode = new ArrayList<>();

    public static void setKey(String key) {
        CONTEXT_HOLD.set(key);
    }

    public static String getKey() {
        return CONTEXT_HOLD.get();
    }

    public static void remove() {
        CONTEXT_HOLD.remove();
    }

    public static void useMasterDataSource() {
        CONTEXT_HOLD.set(DataSourceKeys.master.name());
    }

    public static void useSlaveDataSource() {
        int i = (int)(Math.random()*3);
        System.out.println("第几个节点"+i);
        CONTEXT_HOLD.set(String.valueOf(slaveNode.get(i)));
    }


}
