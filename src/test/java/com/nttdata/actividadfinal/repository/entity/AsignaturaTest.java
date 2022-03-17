package com.nttdata.actividadfinal.repository.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class AsignaturaTest {

//	@Test
//	void test() {
//		Empleado e1 = new Empleado();
//		
//		e1.setId(1);
//		assertEquals(1, e1.getId(), "Mismo id");
//		
//		String nombre = "Nombre Prueba";
//		e1.setNombre(nombre);
//		assertEquals(nombre, e1.getNombre(), "Mismo error");
//		
//		String apellidos = "Apellidos Prueba";
//		e1.setApellidos(apellidos);
//		assertEquals(apellidos, e1.getApellidos(), "Mismos apellidos");
//		
//		Empleado e2 = new Empleado();
//		e2.setId(1);
//		e2.setNombre(nombre);
//		e2.setApellidos(apellidos);
//		assertEquals(e1, e2, "Mismo empleado");
//		
//		assertEquals(e1.hashCode(), e2.hashCode(), "Mismo hash code");
//		
//		assertEquals(e1, e1, "Mismo objeto");
//		assertNotEquals(e1, nombre, "Distinto objeto");
//	}
	
	@Test
	void test() {
		Asignatura a1 = new Asignatura();
		a1.setId(1);
		assertEquals(1, a1.getId(), "Mismo id");
		
		String nombre = "Nombre";
		a1.setNombre(nombre);
		assertEquals(nombre, a1.getNombre());
		
		String descripcion = "Descripción";
		a1.setDescripcion(descripcion);
		assertEquals(descripcion, a1.getDescripcion(), "Misma descripción");
		
		a1.setCurso(3);
		assertEquals(3, a1.getCurso(), "Mismo curso");
		
		Asignatura a2 = new Asignatura();
		a2.setId(1);
		a2.setCurso(3);
		a2.setDescripcion(descripcion);
		a2.setNombre(nombre);
		
		assertEquals(a1, a1, "Misma asignatura");
		
		assertEquals(a1.hashCode(), a2.hashCode(), "Mismo hashcode");
		
		assertEquals(a1,  a1, "Mismo Objeto");
		assertNotEquals(a1, nombre, "Distinto objeto");
	}
}
