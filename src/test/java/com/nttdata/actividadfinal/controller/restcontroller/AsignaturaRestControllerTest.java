package com.nttdata.actividadfinal.controller.restcontroller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import com.nttdata.actividadfinal.repository.AsignaturaRepoJPA;
import com.nttdata.actividadfinal.repository.entity.Asignatura;
import com.nttdata.actividadfinal.service.AsignaturaService;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class AsignaturaRestControllerTest {
	
	private Asignatura a1, a2;
	
	@Autowired
	AsignaturaRepoJPA repo;
	
	@Autowired
	AsignaturaService service;
	
	@Autowired
	AsignaturaRestController controller;
	
	@Mock
	AsignaturaService serviceMock;
	
	@InjectMocks
	AsignaturaRestController controllerMock;

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
	void testConsultarTodos() {
		//GIVEN:
		assertEquals(2, service.listarAsignaturas().size(), "2 Asignaturas");
	
	//WHEN:
		ResponseEntity<List<Asignatura>> l = controller.consultarTodos();
	
	//THEN:
		assertEquals(2, l.getBody().size(), "Hay 2 asignaturas");
	}

	@Test
	void testConsultarPorID() {
		//GIVEN:
		assertEquals(2, service.listarAsignaturas().size(), "Hay 2 asign");
	
//	//WHEN:
		ResponseEntity<Asignatura> asig = controller.consultarPorID(a1.getId());
		
//	//THEN:
		assertEquals(a1, asig.getBody(), "Asig a1");
		assertThat(asig.getStatusCodeValue()).isEqualTo(200);
		assertThat(asig.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void testEliminarTodos() {
		//GIVEN:
		assertEquals(2, service.listarAsignaturas().size(), "2 asig");
		
		//WHEN:
		ResponseEntity<?> asig = controller.eliminarTodos();
		
		//THEN:
		assertEquals(0, service.listarAsignaturas().size(), "0 Asigs");
		assertThat(asig.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	void testEliminarPorId() {
		//GIVEN:
		assertEquals(2, service.listarAsignaturas().size(), "2 asig");
		
	//WHEN:
		ResponseEntity<?> asig = controller.eliminarPorId(a1.getId());
		
	//THEN:
		assertEquals(1, service.listarAsignaturas().size(), "Solo queda 1");
		assertThat(asig.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	void testModificar() {
		//GIVEN:
		assertEquals(2,  service.listarAsignaturas().size(), "2 asigs");
	
	//WHEN:
		String nombre = "Nombre nuevo";
		a1.setNombre(nombre);
		ResponseEntity<Asignatura> asig = controller.modificar(a1.getId(), a1);
	
	//THEN:
		assertEquals(2, service.listarAsignaturas().size(), "2 asigs");
		assertEquals(nombre, service.getById(a1.getId()).getNombre(), "Modificado");
		assertThat(asig.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	void testInsertarFallo() {
		//GIVEN
		assertEquals(2,  service.listarAsignaturas().size(), "2 asigs");
		
		//WHEN
		Asignatura asigFailed = new Asignatura();
		asigFailed.setId(2);
		asigFailed.setCurso(2);
		asigFailed.setDescripcion("fdsaf");
		asigFailed.setNombre("Nombreee");
		ResponseEntity<Asignatura> aController = controller.insertar(asigFailed);
		
		//THEN
		assertEquals(2, service.listarAsignaturas().size(), "2 asigs");
		assertThat(aController.getStatusCodeValue()).isEqualTo(400);
		
		
	}
	
	@Test
	void testInsertar() {
		//GIVEN
		assertEquals(2,  service.listarAsignaturas().size(), "2 asigs");
		
		//WHEN
		Asignatura asig = new Asignatura();
		asig.setCurso(2);
		asig.setDescripcion("fdsaf");
		asig.setNombre("Nombreee");
		ResponseEntity<Asignatura> aController = controller.insertar(asig);
		
		//THEN
		assertEquals(3, service.listarAsignaturas().size(), "3 asigs");
		assertThat(aController.getStatusCodeValue()).isEqualTo(200);
	}

}
