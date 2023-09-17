package com.utn.Practico1.repositorios;

import com.utn.Practico1.entidades.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DomicilioRepositorio extends JpaRepository<Domicilio,Long> {
}
