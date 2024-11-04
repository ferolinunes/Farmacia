package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Drogaria;
import com.example.demo.services.DrogariaService;

@RestController
@RequestMapping("/drogaria")
public class DrogariaController {
	private final DrogariaService drogariaservice;

	@Autowired
	public DrogariaController(DrogariaService drogariaservice) {
		this.drogariaservice = drogariaservice;
	}

	@PostMapping("/salvar")
	public Drogaria criarDrogaria(@RequestBody Drogaria drogaria) {
		return drogariaservice.salvarDrogaria(drogaria);
	}

	@GetMapping
	public List<Drogaria> buscarTodos() {
		return drogariaservice.buscarTodosUsuarios();
		}

	@GetMapping("/{id}")
	public Drogaria buscarPorId(@PathVariable Long id) {
		return drogariaservice.buscarDrogariaPorId(id);
	}

	@DeleteMapping("/{id}")
	public void deleteDrogaria(@PathVariable Long id) {
		drogariaservice.excluirDrogaria(id);
	}

	@PutMapping
	public ResponseEntity<Drogaria> atualizarDrogaria(@PathVariable Long id, @RequestBody Drogaria drogaria) {
		Drogaria drogariaatualizado = drogariaservice.atualizarDrogaria(id, drogaria);
		if (drogariaatualizado != null) {
			return ResponseEntity.ok(drogariaatualizado);
		} else {
			return null;
		}
	}


}
