package com.drz.place.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.drz.place.persistence.sender.PlaceSender;
import com.drz.place.persistence.sender.PlaceSenderRabbitMQImpl;
import com.drz.place.persistence.sender.PlaceSenderSNSImpl;
import lombok.Data;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "place.sender")
@Data
public class PlaceSenderConfig {

    private String topicExchangeName;

    private String queueName;

    private String topicName;

    private String topicDeleteName;

    @Value("${cloud.aws.credentials.accessKey}")
    private String awsCredentialKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String awsCredentialSecret;

    @Bean
    Queue queue() {
        return new Queue(queueName);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding bindingSave(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(topicName);
    }

    @Bean
    Binding bindingDelete(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(topicDeleteName);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        return rabbitTemplate;
    }
//
//    @Bean
//    PlaceSender placeSender(RabbitTemplate rabbitTemplate) {
//        return new PlaceSenderRabbitMQImpl(rabbitTemplate, topicExchangeName, topicName, topicDeleteName);
//    }

    @Bean
    public NotificationMessagingTemplate notificationMessagingTemplate(
            AmazonSNS amazonSNS) {
        return new NotificationMessagingTemplate(amazonSNS);
    }

    @Bean
    PlaceSender placeSender(NotificationMessagingTemplate messagingTemplate) {
        return new PlaceSenderSNSImpl(messagingTemplate, topicExchangeName, topicName, topicDeleteName);
    }
}
