package com.zhen.reggie.test;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class JedisTest {

    @Test
    public void testRedis(){
        //创建连接
        Jedis jedis = new Jedis("localhost", 6379);

        //执行具体操作
        jedis.set("username", "xiaoming");

        String value = jedis.get("username");
        System.out.println(value);

        //jedis.del("username");

        jedis.hset("myhash", "addr", "beijing");
        String hval = jedis.hget("myhash", "addr");
        System.out.println(hval);

        Set<String> keys = jedis.keys("*");


        //关闭连接
        jedis.close();
    }
}
