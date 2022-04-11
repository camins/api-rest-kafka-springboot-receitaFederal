package br.com.camila.receita.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaReceitaDTO {

	private Short id;
	private String cidade;
	private String estado;
}
