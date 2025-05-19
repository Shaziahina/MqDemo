package com.sample.demo_mq.service;

import com.sample.demo_mq.entity.Message;
import com.sample.demo_mq.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessageServiceTest {

    private MessageRepository messageRepository;
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        messageRepository = mock(MessageRepository.class);
        messageService = new MessageService(messageRepository);
    }

    @Test
    void testProcessAndSaveMessage_withContent() {
        String content = "Test message";
        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);

        messageService.processAndSaveMessage(content);

        verify(messageRepository, times(1)).save(captor.capture());
        Message savedMessage = captor.getValue();
        assertEquals(content, savedMessage.getContent());
        assertNotNull(savedMessage.getReceivedAt());
        assertTrue(savedMessage.getReceivedAt().isBefore(LocalDateTime.now().plusSeconds(1)));
    }

    @Test
    void testProcessAndSaveMessage_withNullContent() {
        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);

        messageService.processAndSaveMessage(null);

        verify(messageRepository, times(1)).save(captor.capture());
        Message savedMessage = captor.getValue();
        assertNull(savedMessage.getContent());
        assertNotNull(savedMessage.getReceivedAt());
        assertTrue(savedMessage.getReceivedAt().isBefore(LocalDateTime.now().plusSeconds(1)));
    }
}