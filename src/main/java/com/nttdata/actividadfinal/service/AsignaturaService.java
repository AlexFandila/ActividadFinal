package com.nttdata.actividadfinal.service;

import java.util.List;

import com.nttdata.actividadfinal.repository.entity.Asignatura;

public interface AsignaturaService {
	public List<Asignatura> listarAsignaturas();
	public Asignatura getById(Integer id);
	public void eliminarTodos();
	public void deleteById(Integer id);
	public Asignatura modificar(Asignatura asignatura);
	public Asignatura insertar(Asignatura asignatura);
}
