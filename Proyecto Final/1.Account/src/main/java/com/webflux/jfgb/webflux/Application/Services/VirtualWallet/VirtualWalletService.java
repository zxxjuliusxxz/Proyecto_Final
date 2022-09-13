package com.webflux.jfgb.webflux.Application.Services.VirtualWallet;

import com.webflux.jfgb.webflux.Domain.VirtualWallet;
import com.webflux.jfgb.webflux.Infrastructure.VirtualWalletRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@Log4j2
public class VirtualWalletService implements IVirtualWalletService {
    private static final Logger logger = LogManager.getLogger(VirtualWallet.class);

    @Autowired
    private VirtualWalletRepository virtualWalletServiceRepository;

    @Override
    public Mono<VirtualWallet> register(VirtualWallet virtualWallet) {
        logger.info("Registrando la billetera virtual: " + virtualWalletServiceRepository);
        return virtualWalletServiceRepository.save(virtualWallet);
    }
    @Override
    public Mono<VirtualWallet> findById(String id) {
        logger.info("Movimiento de la billetera virtual: " + virtualWalletServiceRepository.findById(id));
        return virtualWalletServiceRepository.findById(id);
    }
    @Override
    public Flux<VirtualWallet> list() {
        logger.info("Listado de movimientos de la billetera virtual");
        return virtualWalletServiceRepository.findAll();
    }

    @Override
    public Mono<VirtualWallet> updater(String id, VirtualWallet virtualWallet) {
        logger.info("Actualizando la cuenta: " + virtualWallet);
        return virtualWalletServiceRepository.findById(id)
                .flatMap(existingCredit -> {
                    existingCredit.setImeiMobile(virtualWallet.getImeiMobile());
                    existingCredit.setEmail(virtualWallet.getEmail());
                    existingCredit.setNroDocument(virtualWallet.getNroDocument());
                    existingCredit.setNroMobile(virtualWallet.getNroMobile());
                    return virtualWalletServiceRepository.save(existingCredit);
                });
    }
}