package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Fornecedor;
import com.example.demo.repositories.FornecedorRepository;



@Service
public class FornecedorService {
	private final FornecedorRepository fornecedorrepository;

	@Autowired
	public FornecedorService(FornecedorRepository fornecedorrepository) {
		this.fornecedorrepository = fornecedorrepository;
	}

	public Fornecedor salvarFornecedor(Fornecedor fornecedor) {
		return fornecedorrepository.save(fornecedor);
	}

	public Fornecedor buscarFornecedorPorId(Long id) {
		return fornecedorrepository.findById(id).orElse(null);
	}

	public List<Fornecedor> buscarTodosUsuarios() {
		return fornecedorrepository.findAll();
	}

	public void excluirFornecedor(Long id) {
		fornecedorrepository.deleteById(id);
	}

	public Fornecedor atualizarFornecedor(Long id, Fornecedor fornecerdoratualizado) {
		Optional<Fornecedor> fornecedorexistente = fornecedorrepository.findById(id);
		if (fornecedorexistente.isPresent()) {
			Fornecedor fornecedor = fornecedorexistente.get();
			fornecedor.setRazaoSocial(fornecerdoratualizado.getRazaoSocial());
			fornecedor.setNomeFantasia(fornecerdoratualizado.getNomeFantasia());
			fornecedor.setCnpj(fornecerdoratualizado.getCnpj());
			fornecedor.setEndereco(fornecerdoratualizado.getEndereco());
			fornecedor.setFone(fornecerdoratualizado.getFone());
			fornecedor.setEmail(fornecerdoratualizado.getEmail());
			fornecedor.setInscricaoEstadual(fornecerdoratualizado.getInscricaoEstadual());
			return fornecedorrepository.save(fornecedor);
		} else {
			return null;
		}
	}
}

