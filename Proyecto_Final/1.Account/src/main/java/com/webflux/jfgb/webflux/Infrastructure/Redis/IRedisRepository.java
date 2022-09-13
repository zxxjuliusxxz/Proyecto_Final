package com.webflux.jfgb.webflux.Infrastructure.Redis;

import com.webflux.jfgb.webflux.Domain.BankAccount;

import java.util.Map;

public interface IRedisRepository {
    Map<String, BankAccount> findAll();
    BankAccount findById(String id);
    void save(BankAccount afiliado);
    void delete(String id);
}