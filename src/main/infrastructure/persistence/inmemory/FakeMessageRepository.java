package main.infrastructure.persistence.inmemory;

import main.domain.entities.Message;
import main.domain.repositories.MessageRepository;
import main.domain.valueObject.ChatId;
import main.domain.valueObject.MessageId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
