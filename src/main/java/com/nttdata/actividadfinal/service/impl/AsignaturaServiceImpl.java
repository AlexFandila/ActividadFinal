package com.nttdata.actividadfinal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.actividadfinal.repository.AsignaturaRepoJPA;
import com.nttdata.actividadfinal.repository.entity.Asignatura;
import com.nttdata.actividadfinal.service.AsignaturaService;

@Service
public class AsignaturaServiceImpl implements AsignaturaService{
	
	@Autowired
	AsignaturaRepoJPA asignaturaDAO;
	
	@Override
	public List<Asignatura> listarAsignaturas() {
		return asignaturaDAO.findAll();
	}

	@Override
	public Asignatura getById(Integer id) {
		return asignaturaDAO.findById(id).get();
	}

	@Override
	public void eliminarTodos() {
		asignaturaDAO.deleteAll();
		
	}

	@Override
	public void deleteById(Integer id) {
		asignaturaDAO.deleteById(id);
		
	}

	@Override
	public Asignatura modificar(Asignatura asignatura) {
		asignaturaDAO.save(asignatura);
		return asignatura;
	}

	@Override
	public Asignatura insertar(Asignatura asignatura) {
		Asignatura asig = asignaturaDAO.save(asignatura);
		return asig;
	}
	

}
