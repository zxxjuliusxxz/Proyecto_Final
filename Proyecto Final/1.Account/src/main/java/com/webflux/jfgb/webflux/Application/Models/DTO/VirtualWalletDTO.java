package com.webflux.jfgb.webflux.Application.Models.DTO;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class VirtualWalletDTO {
    private String nroDocument; //(DNI, CEX, Pasaporte)
    private Integer nroMobile; // NUMERO DE CELULAR
    private Integer imeiMobile; //
    private String email; // Correo electronico
}
