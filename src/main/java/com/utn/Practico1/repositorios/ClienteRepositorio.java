package com.utn.Practico1.repositorios;

import com.utn.Practico1.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ClienteRepositorio extends JpaRepository<Cliente,Long> {
}
