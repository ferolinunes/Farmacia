package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Drogaria;
import com.example.demo.repositories.DrogariaRepository;



@Service
public class DrogariaService {
	private final DrogariaRepository drogariarepository;

	@Autowired
	public DrogariaService(DrogariaRepository drogariarepository) {
		this.drogariarepository = drogariarepository;
	}

	public Drogaria salvarDrogaria(Drogaria drogaria) {
		return drogariarepository.save(drogaria);
	}

	public Drogaria buscarDrogariaPorId(Long id) {
		return drogariarepository.findById(id).orElse(null);
	}

	public List<Drogaria> buscarTodosUsuarios() {
		return drogariarepository.findAll();
	}

	public void excluirDrogaria(Long id) {
		drogariarepository.deleteById(id);
	}

	public Drogaria atualizarDrogaria(Long id, Drogaria drogariaatualizado) {
		Optional<Drogaria> drogariaexistente = drogariarepository.findById(id);
		if (drogariaexistente.isPresent()) {
			Drogaria drogaria = drogariaexistente.get();
			drogaria.setNome(drogariaatualizado.getNome());
			drogaria.setCnpj(drogariaatualizado.getCnpj());
			return drogariarepository.save(drogaria);
		} else
			return null;
	}

	

}
