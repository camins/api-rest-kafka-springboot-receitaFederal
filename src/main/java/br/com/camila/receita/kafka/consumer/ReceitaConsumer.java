package br.com.camila.receita.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.camila.receita.domain.PessoaReceitaDTO;
import br.com.camila.receita.domain.ReplyCpfReceitaDTO;
import br.com.camila.receita.kafka.producer.ReceitaProducer;
import br.com.camila.receita.service.PessoaService;

@Service
public class ReceitaConsumer {

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private ReceitaProducer receitaProducer;
	
	@KafkaListener(topics = "${topic.name.consumer}", 
			groupId = "${spring.kafka.consumer.group-id}",
			containerFactory = "kafkaListenerContainerFactory")
	public void consumer(PessoaReceitaDTO pessoaDto) {
		String cpf = pessoaService.gerarCPF(pessoaDto);
		
		ReplyCpfReceitaDTO reply = ReplyCpfReceitaDTO.builder()
				.cpf(cpf)
				.id(pessoaDto.getId())
				.build();
		
		receitaProducer.send(reply);
	}
}
