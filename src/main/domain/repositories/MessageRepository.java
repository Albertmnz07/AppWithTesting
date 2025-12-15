package main.domain.repositories;

import main.domain.entities.Message;
import main.domain.valueObject.ChatId;
import main.domain.valueObject.MessageId;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {
    void save(Message message);
    Optional<Message> findById(MessageId messageId);
    List<Message> findByChatId(ChatId chatId);
}
