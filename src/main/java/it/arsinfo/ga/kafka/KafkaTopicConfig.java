package it.arsinfo.ga.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    
    @Value("${operazione.kafka.topic}")
    private String operazioneAttrezzaturaTopic;

    @Bean
    public NewTopic operazioneAttrezzaturaTopic() {
        return TopicBuilder.name(operazioneAttrezzaturaTopic).build();
    }
}
