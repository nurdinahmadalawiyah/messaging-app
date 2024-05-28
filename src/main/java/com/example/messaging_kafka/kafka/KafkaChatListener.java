package com.example.messaging_kafka.kafka;

import com.example.messaging_kafka.chat.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaChatListener {
    private final SimpMessageSendingOperations messagingTemplate;

    @KafkaListener(topics = "chat_messages", groupId = "chat-group")
    public void listen(ChatMessage chatMessage) {
        log.info("Received Message: " + chatMessage);
        // Send the message to the WebSocket topic
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
}
