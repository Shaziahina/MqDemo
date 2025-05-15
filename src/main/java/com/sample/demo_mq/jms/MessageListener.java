package com.sample.demo_mq.jms;

import com.sample.demo_mq.service.MessageService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageListener {

    private final MessageService messageService;
    
    @JmsListener(destination = "message.queue")
    public void receiveMessage(String content) {
        System.out.println("Received message: " + content);
        messageService.processAndSaveMessage(content);
    }
}