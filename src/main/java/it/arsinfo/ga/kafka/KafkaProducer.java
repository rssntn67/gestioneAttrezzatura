package it.arsinfo.ga.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

import it.arsinfo.ga.model.kafka.KafkaOperazione;

@Configuration
@Service
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    @Value("${operazione.kafka.topic}")
    private String operazioneAttrezzaturaTopic;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap;

    @Bean
    public Map<String, Object> producerConfigs() {
      Map<String, Object> props = new HashMap<>();
      log.info("producerConfigs: {}", bootstrap);
      props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrap);
      props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class);
      props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        JsonSerializer.class);
      return props;
    }
  
    @Bean
    public ProducerFactory<String, KafkaOperazione> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }
    
    @Bean
    public KafkaTemplate<String, KafkaOperazione> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
    
    public void send(KafkaOperazione o) {
        kafkaTemplate().send(operazioneAttrezzaturaTopic, o.getKey(),o);
    }
    
}
