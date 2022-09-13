package com.webflux.jfgb.webflux.Controllers;

import com.webflux.jfgb.webflux.Application.Services.VirtualWallet.IVirtualWalletService;
import com.webflux.jfgb.webflux.Domain.VirtualWallet;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
@RestController
@RequestMapping("/api/v1/virtualWallet")
public class VirtualWalletController {

    @Autowired
    private IVirtualWalletService virtualWalletService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<Flux<VirtualWallet>> getVirtualWalletService() {
        return ResponseEntity.ok(virtualWalletService.list());
    }

    @PostMapping(value = "/insert")
    public Mono<ResponseEntity<VirtualWallet>> addAccount(@RequestBody VirtualWallet virtualWallet) {
        return virtualWalletService.register(virtualWallet)
                .map(c -> ResponseEntity.status(HttpStatus.CREATED).body(c));
    }

    @GetMapping("/getById/{id}")
    public Mono<ResponseEntity<VirtualWallet>> getAccountBank(@PathVariable String id) {
        return virtualWalletService.findById(id)
                .map(accountBank -> ResponseEntity.ok(accountBank))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<VirtualWallet>> updateAccountBank(@PathVariable String id,
                                                               @RequestBody VirtualWallet virtualWallet) {
        return virtualWalletService.updater(id, virtualWallet)
                .map(mapper -> ResponseEntity.ok(mapper))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
