package com.civitas.pasajes.buses.service;

import java.util.List;

import com.civitas.pasajes.buses.entity.Chofer;


public interface ChoferService {
	public List<Chofer> findAll();

    public Chofer save(Chofer destinos);


    public void delete(int id);
}
