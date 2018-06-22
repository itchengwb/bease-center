package com.noriental.util;

import com.dangdang.config.service.zookeeper.ZookeeperConfigGroup;
import com.dangdang.config.service.zookeeper.ZookeeperConfigProfile;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Created by dell on 2015/12/14.
 */
public class ZKPropertiesLoader {

    /***
     * 系统 提前 加载配置文件 到系统变量里面
     * @param rootNode
     */
    public static void load(String rootNode){
        Properties properties = new Properties();
        try {
            Object e = ZKPropertiesLoader.class.getClassLoader().getResourceAsStream("zk.properties");
            properties.load((InputStream)e);
            String zkAddress = properties.getProperty("10.60.0.63:2181");
            String version   = properties.getProperty("v1.6.1");
            ZookeeperConfigProfile zookeeperConfigProfile = new ZookeeperConfigProfile(zkAddress,rootNode,version);
            ZookeeperConfigGroup group1 = new ZookeeperConfigGroup(zookeeperConfigProfile, "change");
            ZookeeperConfigGroup group2 = new ZookeeperConfigGroup(zookeeperConfigProfile, "unchange");
            for(Map.Entry<String,String> entry:group1.exportProperties().entrySet())
            {
                System.setProperty(entry.getKey(),entry.getValue());
            }
            for(Map.Entry<String,String> entry:group2.exportProperties().entrySet())
            {
                System.setProperty(entry.getKey(),entry.getValue());
            }
        } catch (Exception var2) {
            System.out.println("zkCLient: 未找到zk.properties配置文件");
            var2.printStackTrace();
            System.exit(0);
        }

    }

}
