package com.utn.Practico1.repositorios;

import com.utn.Practico1.entidades.Rubro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RubroRepositorio extends JpaRepository<Rubro,Long> {
}
