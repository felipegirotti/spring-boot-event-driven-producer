package com.drz.place.persistence.sender;

import com.drz.place.dto.place.PlaceDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class PlaceSenderRabbitMQImpl implements PlaceSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceSenderRabbitMQImpl.class);

    private RabbitTemplate rabbitTemplate;

    private String topicExchangeName;

    private String topicName;

    private String topicDeleteName;

    private ObjectMapper objectMapper;

    public PlaceSenderRabbitMQImpl(RabbitTemplate rabbitTemplate, String topicExchangeName, String topicName, String topicDeleteName) {
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchangeName = topicExchangeName;
        this.topicName = topicName;
        this.topicDeleteName = topicDeleteName;
        objectMapper = new ObjectMapper();
    }

    public void sendSave(PlaceDTO placeDTO) {
        send(placeDTO, topicName);
    }

    public void sendDelete(PlaceDTO placeDTO) {
        send(placeDTO, topicDeleteName);
    }

    private void send(PlaceDTO placeDTO, String topicName) {
        try {
            String msg = objectMapper.writeValueAsString(placeDTO);
            rabbitTemplate.convertAndSend(topicExchangeName, topicName, placeDTO);
        } catch (JsonProcessingException ex) {
            LOGGER.error("Not possible send message to broker, topicExchangeName [%s] topicName [%s]", topicExchangeName, topicName, ex);
        }

    }
}
