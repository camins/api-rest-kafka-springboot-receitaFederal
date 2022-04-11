package br.com.camila.receita.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.camila.receita.domain.ReplyCpfReceitaDTO;

@Service
public class ReceitaProducer {

	private static final Logger logger = LoggerFactory.getLogger(ReceitaProducer.class); 
	private final String topic;
	private final KafkaTemplate<String, ReplyCpfReceitaDTO> kafkaTemplate;
	
	public ReceitaProducer(@Value("${topic.name.producer}") String topic, KafkaTemplate<String, ReplyCpfReceitaDTO> kafkaTemplate) {
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void send(ReplyCpfReceitaDTO reply) {
		kafkaTemplate.send(topic, reply).addCallback(
				success -> logger.info("CPF "+ reply.getId()+" enviado. " +success.getProducerRecord().value()),
				failure -> logger.info("CPF "+ reply.getId()+" não enviado. " + failure.getMessage())
				);
	}
}
