package com.drz.place.persistence.sender;

import com.drz.place.dto.place.PlaceDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class PlaceSenderRabbitMQImpl implements PlaceSender {

    private RabbitTemplate rabbitTemplate;

    private String topicExchangeName;

    private String topicName;

    private String topicDeleteName;

    public PlaceSenderRabbitMQImpl(RabbitTemplate rabbitTemplate, String topicExchangeName, String topicName, String topicDeleteName) {
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchangeName = topicExchangeName;
        this.topicName = topicName;
        this.topicDeleteName = topicDeleteName;
    }

    public void sendSave(PlaceDTO placeDTO) {
        send(placeDTO, topicName);
    }

    public void sendDelete(PlaceDTO placeDTO) {
        send(placeDTO, topicDeleteName);
    }

    private void send(PlaceDTO placeDTO, String topicName) {
        rabbitTemplate.convertAndSend(topicExchangeName, topicName, placeDTO);
    }
}
