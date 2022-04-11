package br.com.camila.receita.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import br.com.camila.receita.domain.ReplyCpfReceitaDTO;

@Configuration
public class KafkaProducerConfig {

	@Value(value= "${spring.kafka.producer.bootstrap-servers}")
	private String bootstrapAdress;
	
	@Value(value= "${topic.name.producer}")
	private String topic;
	
	@Bean
	public ProducerFactory<String, ReplyCpfReceitaDTO> receitaProducerFactory(){
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAdress);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(configProps);
	}
	
	@Bean
	public KafkaTemplate<String, ReplyCpfReceitaDTO> receitaKafkaTemplate(){
		return new KafkaTemplate<>(receitaProducerFactory());
	}
	
}
