package com.webflux.jfgb.webflux.Application.Services.VirtualWallet.Redis;

import com.webflux.jfgb.webflux.Domain.BankAccount;
import com.webflux.jfgb.webflux.Infrastructure.Redis.IRedisRepository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.UUID;

@Repository
public class RedisRepositoryImpl implements IRedisRepository {

    private static final String KEY = "bankAccount";
    private RedisTemplate<String, BankAccount> redisTemplate;
    private HashOperations hashOperations;

    public RedisRepositoryImpl(RedisTemplate<String, BankAccount> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Map<String, BankAccount> findAll() {
        return hashOperations.entries(KEY);
    }

    @Override
    public BankAccount findById(String id) {
        return (BankAccount)hashOperations.get(KEY, id);
    }

    @Override
    public void save(BankAccount bankAccount) {
        hashOperations.put(KEY, UUID.randomUUID().toString(), bankAccount);
    }

    @Override
    public void delete(String id) {
        hashOperations.delete(KEY, UUID.randomUUID().toString(), id);
    }
}