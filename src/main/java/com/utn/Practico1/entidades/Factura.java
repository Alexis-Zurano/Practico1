package com.utn.Practico1.entidades;

import com.utn.Practico1.enums.FormaPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Factura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura;
    private int numero;
    @Temporal(TemporalType.DATE)
    private Date fechaFactura;
    private Double descuento;
    private FormaPago formaPago;
    private int total;
}
