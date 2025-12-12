package domain.repositories;

import domain.entities.Message;
import domain.valueObject.MessageId;

import java.util.Optional;

public interface MessageRepositorie {
    void save(Message message);
    Optional<Message> findById(MessageId messageId);
}
