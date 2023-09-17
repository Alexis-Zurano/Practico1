package com.utn.Practico1.repositorios;

import com.utn.Practico1.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PedidoRepositorio extends JpaRepository<Pedido,Long> {
}
