package com.utn.Practico1.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default
    private List<Domicilio> domicilios = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

    public void agregarDomicilio(Domicilio domi){
        domicilios.add(domi);
    }
    public void mostrarDomicilios(){
        System.out.println("Domicilios de " + nombre + " " + apellido + ": ");
        for (Domicilio domicilio : domicilios){
            System.out.println("calle: " + domicilio.getCalle() + ", Numero: " + domicilio.getNumero());
        }
    }

    public void agregarPedido(Pedido pedi){
        pedidos.add(pedi);
    }
    public void mostrarPedido(){
        System.out.println("Pedido de " + nombre + " " + apellido + ": ");
        for (Pedido pedido : pedidos){
            System.out.println("Fecha " + pedido.getFechaPedido() + ",Total " + pedido.getTotal() + ", detalle de pedido ");
            int counter = 0;
            for (DetallePedido detalle: pedido.getDetallesPedido()){
                counter += 1;
                System.out.println("Producto "+counter+": "+detalle.getProducto().getDenominacion()+"  cantidad: "+detalle.getCantidad()+"  subtotal: "+detalle.getSubtotal());
            }
        }
    }
}
