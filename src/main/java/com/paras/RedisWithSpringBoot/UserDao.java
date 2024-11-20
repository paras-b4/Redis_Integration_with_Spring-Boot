package com.paras.RedisWithSpringBoot;

import ch.qos.logback.core.util.TimeUtil;
import io.lettuce.core.dynamic.domain.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Timer;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Repository
public class UserDao {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    private static final String KEY="User";
    private static final long TTL =10;

    public User save(User user){
        user.setUserId((UUID.randomUUID().toString()));
        redisTemplate.opsForHash().put(KEY,user.getUserId(),user);
        redisTemplate.expire(KEY,TTL, TimeUnit.SECONDS);
        return user;
    }
    public User get(String userId){
        return (User) redisTemplate.opsForHash().get(KEY,userId);
    }
    public Map<Object,Object> findAll(){
        return redisTemplate.opsForHash().entries(KEY);
    }
    public void delete(String userId){
         redisTemplate.opsForHash().delete(KEY,userId);
    }

}
