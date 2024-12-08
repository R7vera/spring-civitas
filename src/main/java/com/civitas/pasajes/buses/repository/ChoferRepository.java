package com.civitas.pasajes.buses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.civitas.pasajes.buses.entity.Chofer;

@Repository
public interface ChoferRepository extends JpaRepository<Chofer, Integer> {



}
