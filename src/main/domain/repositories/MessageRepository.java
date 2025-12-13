package main.domain.repositories;

import main.domain.entities.Message;
import main.domain.valueObject.MessageId;

import java.util.Optional;

public interface MessageRepository {
    void save(Message message);
    Optional<Message> findById(MessageId messageId);
}
