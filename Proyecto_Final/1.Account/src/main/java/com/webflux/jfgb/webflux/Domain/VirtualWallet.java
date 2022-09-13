package com.webflux.jfgb.webflux.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "yanki")
public class VirtualWallet {

    @Id
    private String nroDocument; //(DNI, CEX, Pasaporte)
    private Integer nroMobile; // NUMERO DE CELULAR
    private Integer imeiMobile; //
    private String email; // Correo electronico
}
