package com.sample.demo_mq;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class DemoMqApplicationTest {

    @Test
    void contextLoads() {
        // This test will pass if the application context loads successfully
    }

    @Test
    void mainMethodRunsWithoutException() {
        assertDoesNotThrow(() -> DemoMqApplication.main(new String[]{}));
    }
}