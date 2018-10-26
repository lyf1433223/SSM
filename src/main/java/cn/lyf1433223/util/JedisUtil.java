package cn.lyf1433223.util;


import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;

@Repository("jedisutil")
public class JedisUtil {

    @Resource
    private JedisPool jedisPool;


    public Jedis GetJedis(){
        return jedisPool.getResource();
    }
    
}
