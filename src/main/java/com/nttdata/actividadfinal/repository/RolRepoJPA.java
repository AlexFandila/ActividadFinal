package com.nttdata.actividadfinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttdata.actividadfinal.repository.entity.Rol;

public interface RolRepoJPA extends JpaRepository<Rol, Integer>{
	Rol findByRol(String rol);
}
