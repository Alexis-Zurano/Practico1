package com.utn.Practico1.repositorios;

import com.utn.Practico1.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductoRepositorio extends JpaRepository<Producto,Long> {
}
