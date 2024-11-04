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

import com.example.demo.entities.Fornecedor;
import com.example.demo.services.FornecedorService;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {
		private final FornecedorService fornecedorservice;

		@Autowired
		public FornecedorController(FornecedorService fornecedorservice) {
			this.fornecedorservice = fornecedorservice;
		}

		@PostMapping("/salvar")
		public Fornecedor criarFornecedor(@RequestBody Fornecedor fornecedor) {
			return fornecedorservice.salvarFornecedor(fornecedor);
		}

		@GetMapping
		public List<Fornecedor> buscarTodos() {
			return fornecedorservice.buscarTodosUsuarios();
			}

		@GetMapping("/{id}")
		public Fornecedor buscarPorId(@PathVariable Long id) {
			return fornecedorservice.buscarFornecedorPorId(id);
		}

		@DeleteMapping("/{id}")
		public void deleteFornecedor(@PathVariable Long id) {
			fornecedorservice.excluirFornecedor(id);
		}

		@PutMapping
		public ResponseEntity<Fornecedor> atualizarFornecedor(@PathVariable Long id, @RequestBody Fornecedor fornecedor) {
			Fornecedor fornecedoratualizado = fornecedorservice.atualizarFornecedor(id, fornecedor);
			if (fornecedoratualizado != null) {
				return ResponseEntity.ok(fornecedoratualizado);
			} else {
				return null;
			}
		}


	}
