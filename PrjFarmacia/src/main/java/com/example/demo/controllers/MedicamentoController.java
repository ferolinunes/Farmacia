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

import com.example.demo.entities.Medicamento;
import com.example.demo.services.MedicamentoService;

@RestController
@RequestMapping("/medicamento")
public class MedicamentoController {
	private final MedicamentoService medicamentoservice;

	@Autowired
	public MedicamentoController(MedicamentoService medicamentoservice) {
		this.medicamentoservice = medicamentoservice;
	}

	@PostMapping("/criar")
	public ResponseEntity<Medicamento> criarMedicamento(@RequestBody Medicamento medicamento) {
		Medicamento medicamentosalvo = medicamentoservice.salvarMedicamento(medicamento);
		if(medicamentosalvo != null) {
			return ResponseEntity.ok(medicamentosalvo);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping
	public List<Medicamento> buscarTodos() {
		return medicamentoservice.buscarTodosMedicamento();
		}

	@GetMapping("/{id}")
	public Medicamento buscarPorId(@PathVariable Long id) {
		return medicamentoservice.buscarMedicamentoPorId(id);
	}

	@DeleteMapping("/{id}")
	public void deleteMedicamento(@PathVariable Long id) {
		medicamentoservice.excluirMedicamento(id);
	}

	@PutMapping ("/{id}")
	public ResponseEntity<Medicamento> atualizarMedicamento(@PathVariable Long id, @RequestBody Medicamento medicamento) {
		Medicamento medicamentoatualizado = medicamentoservice.atualizarMedicamento(id, medicamento);
		if (medicamentoatualizado != null) {
			return ResponseEntity.ok(medicamentoatualizado);
		} else {
			return null;
		}
	}

}

