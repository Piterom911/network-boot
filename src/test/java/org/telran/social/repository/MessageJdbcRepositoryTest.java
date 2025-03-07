package org.telran.social.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.telran.social.enums.MessageStatus;
import org.telran.social.model.Message;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageJdbcRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    void testGetByUserId() {
        List<Message> messages = messageRepository.getAllByUserId(1L);
    }

    @Test
    void testBetById() {
        Message message = messageRepository.getById(1L);
    }

    @Test
    void testCreat() {
        List<Message> allMessagesBefore = messageRepository.getAllByUserId(1L);
        Message message = new Message(null, "test text", 1L, 2L, MessageStatus.DRAFT);
        Long messageId = messageRepository.create(message).getId();
        List<Message> allMessagesAfter = messageRepository.getAllByUserId(1L);
        assertEquals(allMessagesBefore.size() + 1, allMessagesAfter.size());
        assertEquals(allMessagesBefore.get(allMessagesBefore.size() - 1).getId() + 1, messageId);
    }

    @Test
    void testDelete() {
        List<Message> allMessagesBefore = messageRepository.getAllByUserId(1L);
        messageRepository.deleteById(1L);
        List<Message> allMessagesAfter = messageRepository.getAllByUserId(1L);
        assertEquals(allMessagesBefore.size() - 1, allMessagesAfter.size());
    }
}