package com.sample.demo_mq.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void testGettersAndSetters() {
        Message message = new Message();
        Long id = 123L;
        String content = "Hello, World!";
        LocalDateTime receivedAt = LocalDateTime.now();

        message.setId(id);
        message.setContent(content);
        message.setReceivedAt(receivedAt);

        assertEquals(id, message.getId());
        assertEquals(content, message.getContent());
        assertEquals(receivedAt, message.getReceivedAt());
    }

    @Test
    void testEqualsAndHashCode() {
        Message m1 = new Message();
        Message m2 = new Message();

        Long id = 1L;
        String content = "Test";
        LocalDateTime receivedAt = LocalDateTime.now();

        m1.setId(id);
        m1.setContent(content);
        m1.setReceivedAt(receivedAt);

        m2.setId(id);
        m2.setContent(content);
        m2.setReceivedAt(receivedAt);

        assertEquals(m1, m2);
        assertEquals(m1.hashCode(), m2.hashCode());

        m2.setId(2L);
        assertNotEquals(m1, m2);
    }

    @Test
    void testToString() {
        Message message = new Message();
        message.setId(1L);
        message.setContent("Test");
        message.setReceivedAt(LocalDateTime.of(2023, 1, 1, 12, 0));

        String toString = message.toString();
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("content=Test"));
        assertTrue(toString.contains("receivedAt=2023-01-01T12:00"));
    }

    @Test
    void testNoArgsConstructor() {
        Message message = new Message();
        assertNull(message.getId());
        assertNull(message.getContent());
        assertNull(message.getReceivedAt());
    }
}