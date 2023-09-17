package com.utn.Practico1.entidades;

import com.utn.Practico1.enums.EstadoPedido;
import com.utn.Practico1.enums.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;
    @Temporal(TemporalType.DATE)
    private Date fechaPedido;
    private Double total;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "factura_id", nullable = true)
    private Factura factura;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private List<DetallePedido> detallesPedido= new ArrayList<>();
    private TipoEnvio tipoEnvio;
    private EstadoPedido estadoPedido;

    public void agregarDetallePedido(DetallePedido detaPedi){
        detallesPedido.add(detaPedi);
    }
    public void mostrarDetallePedido(){
        for (DetallePedido detaPedido : detallesPedido){
            System.out.println("Cantidad " + detaPedido.getCantidad() + ", subtotal " + detaPedido.getSubtotal()+ ", producto:" + detaPedido.getProducto().getDenominacion());

        }
    }
}
