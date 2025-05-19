
package com.sample.demo_mq.jms;

import com.sample.demo_mq.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

class MessageListenerTest {

    private MessageService messageService;
    private MessageListener messageListener;

    @BeforeEach
    void setUp() {
        messageService = mock(MessageService.class);
        messageListener = new MessageListener(messageService);
    }

    @Test
    void testReceiveMessage_callsServiceAndPrintsOutput() {
        String testContent = "Test message";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        messageListener.receiveMessage(testContent);

        System.setOut(originalOut);

        verify(messageService, times(1)).processAndSaveMessage(testContent);
        assertTrue(outContent.toString().contains("Received message: " + testContent));
    }

    @Test
    void testReceiveMessage_withNull_callsServiceAndPrintsOutput() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        messageListener.receiveMessage(null);

        System.setOut(originalOut);

        verify(messageService, times(1)).processAndSaveMessage(null);
        assertTrue(outContent.toString().contains("Received message: null"));
    }

    @Test
    void testClassIsAnnotatedWithComponent() {
        assertTrue(MessageListener.class.isAnnotationPresent(Component.class));
    }

    @Test
    void testReceiveMessageIsAnnotatedWithJmsListener() throws NoSuchMethodException {
        Method method = MessageListener.class.getMethod("receiveMessage", String.class);
        assertTrue(method.isAnnotationPresent(JmsListener.class));
        JmsListener annotation = method.getAnnotation(JmsListener.class);
        assertEquals("message.queue", annotation.destination());
    }
}
