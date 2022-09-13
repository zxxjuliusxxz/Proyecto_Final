package com.webflux.jfgb.webflux.Application.Services.VirtualWallet;

import com.webflux.jfgb.webflux.Domain.VirtualWallet;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IVirtualWalletService {

    Flux<VirtualWallet> list();

    Mono<VirtualWallet> register(VirtualWallet virtualWallet);

    Mono<VirtualWallet> findById(String id);

    Mono<VirtualWallet> updater(String id, VirtualWallet virtualWallet);
}