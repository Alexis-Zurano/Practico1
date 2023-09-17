package com.utn.Practico1.repositorios;

import com.utn.Practico1.entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FacturaRepositorio extends JpaRepository<Factura,Long> {
}
