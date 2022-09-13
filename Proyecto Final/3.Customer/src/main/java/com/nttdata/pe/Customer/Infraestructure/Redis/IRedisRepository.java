package com.nttdata.pe.Customer.Infraestructure.Redis;

import com.nttdata.pe.Customer.Domain.Customer;
import java.util.Map;

public interface IRedisRepository {
    Map<String, Customer> findAll();
    Customer findById(String id);
    void save(Customer customer);
    void delete(String id);
}