package com.radtke.bartosz.query.config;

import com.radtke.bartosz.query.model.dto.CommentWithPostIdDto;
import com.radtke.bartosz.query.model.dto.PostDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public Map<String, Object> consumerConfigs() {
        return Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class
        );
    }

    @Bean
    public ConsumerFactory<String, CommentWithPostIdDto> commentConsumerFactory() {
        JsonDeserializer<CommentWithPostIdDto> jsonDeserializer = new JsonDeserializer<>(CommentWithPostIdDto.class, false);
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(),
                new StringDeserializer(),
                jsonDeserializer);
    }

    @Bean
    public KafkaListenerContainerFactory<
            ConcurrentMessageListenerContainer<String, CommentWithPostIdDto>
            > commentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CommentWithPostIdDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(commentConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, PostDto> postConsumerFactory() {
        JsonDeserializer<PostDto> jsonDeserializer = new JsonDeserializer<>(PostDto.class, false);
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(),
                new StringDeserializer(),
                jsonDeserializer);
    }
    @Bean
    public KafkaListenerContainerFactory<
            ConcurrentMessageListenerContainer<String, PostDto>
            > postKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PostDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(postConsumerFactory());
        return factory;
    }

}
