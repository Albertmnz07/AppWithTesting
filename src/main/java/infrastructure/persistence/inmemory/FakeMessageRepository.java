package main.java.infrastructure.persistence.inmemory;

import main.java.domain.entities.Message;
import main.java.domain.repositories.MessageRepository;
import main.java.domain.valueObject.ChatId;
import main.java.domain.valueObject.MessageId;

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
