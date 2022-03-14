package com.nttdata.actividadfinal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nttdata.actividadfinal.repository.entity.Usuario;
import com.nttdata.actividadfinal.service.AsignaturaService;
import com.nttdata.actividadfinal.service.UsuarioService;

@Controller
public class AsignaturaController {

	@Autowired
	AsignaturaService asignaturaService;

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/")
	public String index(Model model) {
		Usuario u = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("usuario", u);
		return "index";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/asignaturas")
	public String listarAsignaturas(Model model) {
		model.addAttribute("listaAsignaturas", asignaturaService.listarAsignaturas());
		return "asignaturas";
	}
	
	@GetMapping("/roladmin")
	public String listarRolAdmin(Model model) {
		model.addAttribute("listaUsuario", usuarioService.buscarPorUsuarioAdmin());
		return "roladmin";
	}
	
	@GetMapping("/rolconsultar")
	public String listarRolConsultar(Model model) {
		model.addAttribute("listaUsuario", usuarioService.buscarPorUsuarioConsultar());
		return "rolconsultar";
	}

}
