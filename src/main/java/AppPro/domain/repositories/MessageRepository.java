package AppPro.domain.repositories;

import AppPro.domain.entities.Message;
import AppPro.domain.valueObject.ChatId;
import AppPro.domain.valueObject.MessageId;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {
    void save(Message message);
    Optional<Message> findById(MessageId messageId);
    List<Message> findByChatId(ChatId chatId);
}
