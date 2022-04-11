package br.com.camila.receita.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.camila.receita.Utils.Utils;
import br.com.camila.receita.domain.PessoaReceita;
import br.com.camila.receita.domain.PessoaReceitaDTO;
import br.com.camila.receita.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private Utils util;
	
	public String gerarCPF(PessoaReceitaDTO pessoaDTO) {
		
		boolean exist = true;
		String cpf = "";
		do {
			cpf = util.cpf();
			boolean b = pessoaRepository.existsByCpf(cpf);
			
			if(!b) {
				exist = !exist; 
			}
			
		} while(exist);
		
		
		PessoaReceita pessoa = PessoaReceita.builder()
				.cpf(cpf)
				.cidade(pessoaDTO.getCidade())
				.estado(pessoaDTO.getEstado())
			.build();
		
		pessoaRepository.save(pessoa);
		
		return cpf;
	}
	
	
	
}
