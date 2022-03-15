package com.nttdata.actividadfinal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nttdata.actividadfinal.repository.RolRepoJPA;
import com.nttdata.actividadfinal.repository.UsuarioRepoJPA;
import com.nttdata.actividadfinal.repository.entity.Rol;
import com.nttdata.actividadfinal.repository.entity.Usuario;
import com.nttdata.actividadfinal.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService{

	@Autowired
	UsuarioRepoJPA usuarioDAO;
	
	@Autowired
	RolRepoJPA rolDAO;

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioDAO.findAll();
	}

	@Override
	public Usuario buscarPorUsername(String username) {
		return usuarioDAO.findById(username).get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return buscarPorUsername(username);
	}

	@Override
	public List<Usuario> buscarPorUsuarioAdmin() {
		
//		Rol rol = rolDAO.findByRol("ADMIN");
		List<Usuario> usuariosAdmin = usuarioDAO.findByRol(new Rol(1, "ADMIN"));

		
		return usuariosAdmin;
	}

	@Override
	public List<Usuario> buscarPorUsuarioConsultar() {
		
//		Rol rol = rolDAO.findByRol("CONSULTA");
		List<Usuario> usuariosConsultar = usuarioDAO.findByRol(new Rol(2, "CONSULTA"));
		
		return usuariosConsultar;
	}

}
