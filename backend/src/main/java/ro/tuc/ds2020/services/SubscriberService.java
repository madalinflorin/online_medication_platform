package ro.tuc.ds2020.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private static final String WS_MESSAGE_TRANSFER_DESTINATION = "/topic/user";

    public SubscriberService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendMessage(String message) {
        simpMessagingTemplate.convertAndSend(WS_MESSAGE_TRANSFER_DESTINATION,message);
    }
}
