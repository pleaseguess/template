package com.springboot.template.jdeis;

import redis.clients.jedis.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jinyu on 2018/9/4.
 */
public class JedisClient {
    private static ShardedJedisPool pool;
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(50);
        config.setMaxWaitMillis(3000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        // 集群
        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("47.98.111.76", 6379);
        //jedisShardInfo1.setPassword("test123");
        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
        list.add(jedisShardInfo1);
        pool = new ShardedJedisPool(config, list);
    }
    public static void main(String[] args){
        ShardedJedis jedis = pool.getResource();
        System.out.println("Server is running :" + jedis.pipelined());
        System.out.println(jedis.get("foo"));
    }
}
