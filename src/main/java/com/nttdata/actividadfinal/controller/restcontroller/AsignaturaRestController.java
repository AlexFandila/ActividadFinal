package com.nttdata.actividadfinal.controller.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.actividadfinal.repository.entity.Asignatura;
import com.nttdata.actividadfinal.service.AsignaturaService;

@RestController
@RequestMapping("/rest/asignaturas")
public class AsignaturaRestController {

	@Autowired
	AsignaturaService asignaturaService;

//	@CacheEvict(value = "empleados", allEntries = true)
//	@PostMapping
//	public ResponseEntity<Empleado> insertarEmpleado_v3(@RequestBody Empleado empleado) {
//		try {
//			HttpHeaders headers = new HttpHeaders();
//			if (empleado.getId() != null) {
//				headers.set("Message", "Para dar de alta un nuevo empleado, el ID debe llegar vacío");
//				return new ResponseEntity<>(headers, HttpStatus.NOT_ACCEPTABLE);
//			} else if (empleado.getNombre() == null || empleado.getNombre().equals("")
//					|| empleado.getApellidos() == null || empleado.getApellidos().equals("")) {
//				headers.set("Message", "Ni NOMBRE ni APELLIDOS pueden ser nulos");
//				return new ResponseEntity<>(headers, HttpStatus.NOT_ACCEPTABLE);
//			}
//
//			Empleado emp = empleadoService.inserta(empleado);
//			URI newPath = new URI("/api/empleados/" + emp.getId());
//			headers.setLocation(newPath);
//			headers.set("Message", "Empleado insertado correctamente con id: " + emp.getId());
//
//			return new ResponseEntity<>(emp, headers, HttpStatus.CREATED);
//		} catch (Exception ex) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	@Cacheable(value = "asignatura")
	@GetMapping
	public ResponseEntity<List<Asignatura>> consultarTodos() {

		try {
			List<Asignatura> asigs = asignaturaService.listarAsignaturas();
			HttpHeaders headers = new HttpHeaders();

			if (asigs == null) {
				headers.set("Message", "No existen datos");
				return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Asignatura>>(asigs, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Cacheable(value = "asignatura")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Asignatura> consultarPorID(@PathVariable Integer id) {
		try {
			HttpHeaders headers = new HttpHeaders();
			Asignatura asig = asignaturaService.getById(id);

			if (asig == null) {
				headers.set("Message", "El usuario con id " + id + " no existe");
				return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Asignatura>(asig, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CacheEvict(value = "asignatura", allEntries = true)
	@DeleteMapping
	public ResponseEntity<?> eliminarTodos() {
		try {
			asignaturaService.eliminarTodos();
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CacheEvict(value = "asignatura", allEntries = true)
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> eliminarPorId(@PathVariable Integer id) {
		try {
			asignaturaService.deleteById(id);
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CacheEvict(value = "asignatura", allEntries = true)
	@PutMapping(value = "/{id}")
	public ResponseEntity<Asignatura> modificar(@PathVariable Integer id, @RequestBody Asignatura asigModificada) {
		try {

			HttpHeaders headers = new HttpHeaders();
			
			return new ResponseEntity<Asignatura>(asignaturaService.modificar(asigModificada), HttpStatus.OK);
			
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@CacheEvict(value = "asignatura", allEntries = true)
	@PostMapping
	public ResponseEntity<Asignatura> insertar(@RequestBody Asignatura asignatura) {
		try {
			HttpHeaders headers = new HttpHeaders();
			if (asignatura.getId() != null) {
				headers.set("Message", "El campo id de asignatura debe estar vacio");
				return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<Asignatura>(asignaturaService.insertar(asignatura), HttpStatus.OK);
			
		}catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
