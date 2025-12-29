package main.java.domain.repositories;

import main.java.domain.entities.Message;
import main.java.domain.valueObject.ChatId;
import main.java.domain.valueObject.MessageId;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {
    void save(Message message);
    Optional<Message> findById(MessageId messageId);
    List<Message> findByChatId(ChatId chatId);
}
