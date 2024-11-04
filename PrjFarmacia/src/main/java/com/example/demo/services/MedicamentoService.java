package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Fornecedor;
import com.example.demo.entities.Medicamento;
import com.example.demo.repositories.FornecedorRepository;
import com.example.demo.repositories.MedicamentoRepository;

@Service
public class MedicamentoService {
	private final MedicamentoRepository medicamentorepository;
    private final FornecedorRepository fornecedorrepository;
	
	@Autowired
	public MedicamentoService(MedicamentoRepository medicamentorepository, FornecedorRepository fornecedorrepository) {
		this.medicamentorepository = medicamentorepository;
		this.fornecedorrepository = fornecedorrepository;
	}

	public Medicamento salvarMedicamento(Medicamento medicamento) {
		if(medicamento.getFornecedor() !=null && medicamento.getFornecedor().getId() !=null ) {
			Optional<Fornecedor> fornecedor = fornecedorrepository.findById(medicamento.getFornecedor().getId()); //variavel fornecedor que vai localizar o fornecedor
			if(fornecedor.isPresent()) {
				medicamento.setFornecedor(fornecedor.get());
				return medicamentorepository.save(medicamento);
			} else {
				throw new RuntimeException("Fornecedor não encontrado");  //uma exceção
			}
		} else {
			throw new RuntimeException("ID do Fornecedor é obrigatório");
		}
	}

	public Medicamento buscarMedicamentoPorId(Long id) {
		return medicamentorepository.findById(id).orElse(null);
	}

	public List<Medicamento> buscarTodosMedicamento() {
		return medicamentorepository.findAll();
	}

	public void excluirMedicamento(Long id) {
		medicamentorepository.deleteById(id);
	}

	public Medicamento atualizarMedicamento(Long id, Medicamento medicamentoatualizado) {
		Optional<Medicamento> medicamentoexistente = medicamentorepository.findById(id);
		if (medicamentoexistente.isPresent()) {
			Medicamento medicamento = medicamentoexistente.get();
			medicamento.setNome(medicamentoatualizado.getNome());
			medicamento.setBula(medicamentoatualizado.getBula());
			medicamento.setDataValidade(medicamentoatualizado.getDataValidade());
			if(medicamentoatualizado.getFornecedor() !=null) {
				medicamento.setFornecedor(medicamentoatualizado.getFornecedor());
			}
			return medicamentorepository.save(medicamento);
		} else
			return null;
	}

	

}
