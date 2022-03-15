package com.nttdata.actividadfinal.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table
public class Rol implements GrantedAuthority{
	
	public Rol() {
		
	}
	
	public Rol(Integer id, String rol) {
		this.id = id;
		this.rol = rol;
	}

	@Id
	@Column
	private Integer id;

	@Column(unique = true)
	private String rol;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String nombre) {
		this.rol = nombre;
	}

	@Override
	public String getAuthority() {
		return ("ROLE_"+this.rol).toUpperCase();
	}
}
