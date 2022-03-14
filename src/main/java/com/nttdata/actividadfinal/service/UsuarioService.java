package com.nttdata.actividadfinal.service;

import java.util.List;

import com.nttdata.actividadfinal.repository.entity.Usuario;

public interface UsuarioService {
	public List<Usuario> listarUsuarios();
	public Usuario buscarPorUsername(String username);
	public List<Usuario> buscarPorUsuarioAdmin();
	public List<Usuario> buscarPorUsuarioConsultar();
}
