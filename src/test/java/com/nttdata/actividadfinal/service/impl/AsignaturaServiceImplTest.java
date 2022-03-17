package com.nttdata.actividadfinal.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.nttdata.actividadfinal.repository.AsignaturaRepoJPA;
import com.nttdata.actividadfinal.repository.entity.Asignatura;
import com.nttdata.actividadfinal.service.AsignaturaService;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class AsignaturaServiceImplTest {
	
	private Asignatura a1, a2;
	
	@Autowired
	AsignaturaRepoJPA repo;
	
	@Autowired
	AsignaturaService service;

	@BeforeEach
	void setUp() throws Exception {
		repo.deleteAll();
		a1 = new Asignatura();
		a1.setNombre("Asignatura");
		a1.setCurso(3);
		a1.setDescripcion("Descripcion");
		a1 = repo.save(a1);
		
		a2 = new Asignatura();
		a2.setNombre("asig");
		a2.setCurso(2);
		a2.setDescripcion("UnaDescripción");
		a2 = repo.save(a2);
	}

	@AfterEach
	void tearDown() throws Exception {
		repo.deleteAll();
	}

	@Test
	void testListarAsignaturas() {
		//GIVEN:
		assertEquals(2, service.listarAsignaturas().size(), "2 asignaturas en BBDD");

	//WHEN:
		List<Asignatura> l = service.listarAsignaturas();

	//THEN:
		assertEquals(2, l.size(), "Hay 2 asignaturas en BBDD");
	}

	@Test
	void testGetById() {
		//GIVEN:
		assertEquals(2, service.listarAsignaturas().size(), "Hay 2 asignaturas");
		
	//WHEN:
		Asignatura a = service.getById(a1.getId());
		
	//THEN:
		assertEquals(a1.getId(), a.getId(), "Misma asignatura");
		assertNotNull(a, "Asignatura no es nulla");
	}

	@Test
	void testEliminarTodos() {
		//GIVEN:
		assertEquals(2, service.listarAsignaturas().size(), "Hay 2 asignaturas");
		
		//WHEN:
		service.eliminarTodos();
		
		//THEN:
		assertEquals(0, service.listarAsignaturas().size(), "No hay asignaturas");
	}

	@Test
	void testDeleteById() {
		//GIVEN:
		assertEquals(2, service.listarAsignaturas().size(), "Hay 2 asignaturas");
		
	//WHEN:
		service.deleteById(a1.getId());
		
	//THEN:
		assertEquals(1, service.listarAsignaturas().size(), "Solo queda 1 asignatura");
//		assertEquals(1, service.listar().size(), "Solo queda 1 usuario");
	}

	@Test
	void testModificar() {
		//GIVEN:
		assertEquals(2, service.listarAsignaturas().size(), "Hay 2 asignaturas");

	//WHEN:
		String nombre = "Nuevo nombre";
		a1.setNombre(nombre);
		service.modificar(a1);
		
	//THEN:
		assertEquals(2, service.listarAsignaturas().size(), "Hay 2 asignaturas");
		assertEquals(nombre, service.getById(a1.getId()).getNombre(), "Nombre modificado");
	}

	@Test
	void testInsertar() {
		//GIVEN:
		assertEquals(2, service.listarAsignaturas().size(), "Hay 2 asignaturas");

	//WHEN:
		Asignatura a = new Asignatura();
		a.setNombre("Asignatura a");
		a.setCurso(5);
		a.setDescripcion("Descripcion a");
		a = service.insertar(a);
		
	//THEN:
		assertEquals(3, service.listarAsignaturas().size(), "3 asign");
	}

}
