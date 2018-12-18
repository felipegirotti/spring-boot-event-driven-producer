package com.drz.place.persistence.sender;

import com.drz.place.dto.place.PlaceDTO;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;

public class PlaceSenderSNSImpl implements PlaceSender {

    private NotificationMessagingTemplate messagingTemplate;

    private String topic;

    private String subjectSave;

    private String subjectDelete;

    public PlaceSenderSNSImpl(NotificationMessagingTemplate messagingTemplate, String topic, String subjectSave, String subjectDelete) {
        this.messagingTemplate = messagingTemplate;
        this.topic = topic;
        this.subjectSave = subjectSave;
        this.subjectDelete = subjectDelete;
    }

    @Override
    public void sendSave(PlaceDTO placeDTO) {
        messagingTemplate.sendNotification(topic, placeDTO, subjectSave);
    }

    @Override
    public void sendDelete(PlaceDTO placeDTO) {
        messagingTemplate.sendNotification(topic, placeDTO, subjectDelete);
    }
}
