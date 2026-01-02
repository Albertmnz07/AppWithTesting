package infrastructure.persistence.inmemory;

import domain.entities.Message;
import domain.repositories.MessageRepository;
import domain.valueObject.ChatId;
import domain.valueObject.MessageId;
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
