package com.team602.foodfortress;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

@SpringBootTest
public class SpringDataRedisTest {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Test
    public void test(){
        System.out.println(redisTemplate);
        ValueOperations valueOperations = redisTemplate.opsForValue();//操作字符串
        HashOperations hashOperations = redisTemplate.opsForHash();//操作hash
        ListOperations listOperations = redisTemplate.opsForList();//操作list
        SetOperations setOperations = redisTemplate.opsForSet();//操作set
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();//操作zset
    }
    @Test
    public void testString(){//操作字符串

        redisTemplate.opsForValue().set("test1","zhangsan");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }

    @Test
    public void testHash(){//操作hash
        redisTemplate.opsForHash().put("user","name","zhangsan");
        redisTemplate.opsForHash().put("user","age",18);
        System.out.println(redisTemplate.opsForHash().get("user","name"));
        System.out.println(redisTemplate.opsForHash().get("user","age"));
    }
}
