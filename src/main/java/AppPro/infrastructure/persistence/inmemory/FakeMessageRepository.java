package AppPro.infrastructure.persistence.inmemory;

import AppPro.domain.entities.Message;
import AppPro.domain.repositories.MessageRepository;
import AppPro.domain.valueObject.ChatId;
import AppPro.domain.valueObject.MessageId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class FakeMessageRepository implements MessageRepository {
    Map<MessageId , Message> storage = new HashMap<>();

    @Override
    public void save(Message message) {
        storage.put(message.getMessageID() , message);
    }

    @Override
    public Optional<Message> findById(MessageId messageId) {
        return Optional.ofNullable(storage.get(messageId));
    }

    @Override
    public List<Message> findByChatId(ChatId chatId) {
        return storage.values().
                stream().
                filter(message -> message.getChatID().
                        equals(chatId)).toList();

    }
}
