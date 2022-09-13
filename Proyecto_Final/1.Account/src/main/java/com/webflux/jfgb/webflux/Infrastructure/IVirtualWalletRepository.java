package com.webflux.jfgb.webflux.Infrastructure;

import com.webflux.jfgb.webflux.Domain.VirtualWallet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface IVirtualWalletRepository extends ReactiveMongoRepository<VirtualWallet, String> {

    Mono<VirtualWallet> findByNroDocument(String number);
}
