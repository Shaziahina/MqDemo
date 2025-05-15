package com.sample.demo_mq.service;

import com.sample.demo_mq.entity.Message;
import com.sample.demo_mq.repository.MessageRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessageService {
    
    private final MessageRepository messageRepository;
    
    public void processAndSaveMessage(String content) {
        Message message = new Message();
        message.setContent(content);
        message.setReceivedAt(LocalDateTime.now());
        messageRepository.save(message);
    }
}