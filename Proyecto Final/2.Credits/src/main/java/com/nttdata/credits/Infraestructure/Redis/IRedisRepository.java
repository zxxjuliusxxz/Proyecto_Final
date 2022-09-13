package com.nttdata.credits.Infraestructure.Redis;
import com.nttdata.credits.Domain.CreditCard;
import java.util.Map;

public interface IRedisRepository {
    Map<String, CreditCard> findAll();
    CreditCard findById(String id);
    void save(CreditCard creditCard);
    void delete(String id);
}