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

public class Rubro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRubro;
    private String denominacion;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    public void agregarProdcutos(Producto produ){
        productos.add(produ);
    }
    public void mostrarProductos(){
        for (Producto product : productos){
            System.out.println( "Producto: " + product.getDenominacion() + ", Tipo de producto: " + product.getTipoProducto() + ", Stock actual: " + product.getStockActual());
        }
    }

}
