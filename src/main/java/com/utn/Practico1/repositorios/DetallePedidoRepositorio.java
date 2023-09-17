package com.utn.Practico1.repositorios;

import com.utn.Practico1.entidades.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DetallePedidoRepositorio extends JpaRepository<DetallePedido,Long> {
}
