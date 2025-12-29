package domain.repositories;

import domain.entities.Message;
import domain.valueObject.ChatId;
import domain.valueObject.MessageId;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {
    void save(Message message);
    Optional<Message> findById(MessageId messageId);
    List<Message> findByChatId(ChatId chatId);
}
