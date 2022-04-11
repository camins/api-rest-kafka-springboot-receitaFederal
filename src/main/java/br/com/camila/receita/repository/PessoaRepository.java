package br.com.camila.receita.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.camila.receita.domain.PessoaReceita;

public interface PessoaRepository extends JpaRepository<PessoaReceita, Short> {

	boolean existsByCpf(String cpf);
	
}
