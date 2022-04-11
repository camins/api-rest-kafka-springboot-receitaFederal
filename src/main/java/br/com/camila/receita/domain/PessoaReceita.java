package br.com.camila.receita.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class PessoaReceita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;
	
	@Column(length = 50, nullable = false)
	private String cidade;
	
	@Column(length = 10, nullable = false)
	private String estado;
	
	@Column(nullable = true, unique = true)
	private String cpf;
	
}
