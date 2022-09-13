package com.webflux.jfgb.webflux.Application.Services.VirtualWallet;

import com.webflux.jfgb.webflux.Domain.VirtualWallet;
import com.webflux.jfgb.webflux.Infrastructure.IVirtualWalletRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.function.Function;
@Service
@Log4j2
public class VirtualWalletService implements IVirtualWalletService {

    private static final Logger logger = LogManager.getLogger(VirtualWalletService.class);
    @Autowired
    private IVirtualWalletRepository virtualWalletRepository;

    @Override
    public Flux<VirtualWallet> list() {
        logger.info("Listado de billetra virtual del banco");
        return virtualWalletRepository.findAll();
    }

    @Override
    public Mono<VirtualWallet> register(VirtualWallet virtualWallet) {
        logger.info("Registrando la billetera virtual: " + virtualWallet);
        Function<Boolean, Mono<VirtualWallet>> exits = (Boolean b) -> {
            if (b) {
                return Mono.error(new Exception("VirtualWallet bank already registered"));
            }
                return virtualWalletRepository.save(virtualWallet);
        };

        return virtualWalletRepository.findByNroDocument(virtualWallet.getNroDocument())
                .hasElement()
                .flatMap(exits);
    }

    @Override
    public Mono<VirtualWallet> findById(String id) {
        logger.info("Billetera virtual encontrada con codigo: " + virtualWalletRepository.findById(id));
        return virtualWalletRepository.findById(id);
    }

    @Override
    public Mono<VirtualWallet> updater(String id, VirtualWallet virtualWallet) {
        logger.info("Actualizando la Billetera virtual: " + virtualWallet);
        return virtualWalletRepository.findById(id)
                .flatMap(existingCredit -> {
                    existingCredit.setEmail(virtualWallet.getEmail());
                    existingCredit.setNroDocument(virtualWallet.getNroDocument());
                    existingCredit.setNroMobile(virtualWallet.getNroMobile());
                    existingCredit.setImeiMobile(virtualWallet.getImeiMobile());
                    return virtualWalletRepository.save(existingCredit);
                });
    }
}