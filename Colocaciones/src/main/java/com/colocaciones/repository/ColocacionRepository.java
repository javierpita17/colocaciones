package com.colocaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colocaciones.model.Colocacion;

@Repository
public interface ColocacionRepository extends JpaRepository<Colocacion, Integer>{

}
