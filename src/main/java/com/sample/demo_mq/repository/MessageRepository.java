package com.sample.demo_mq.repository;

import com.sample.demo_mq.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}