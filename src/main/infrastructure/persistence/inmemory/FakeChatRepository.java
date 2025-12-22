package main.infrastructure.persistence.inmemory;

import main.domain.entities.Chat;
import main.domain.repositories.ChatRepository;
import main.domain.valueObject.ChatId;
import main.domain.valueObject.UserId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeChatRepository implements ChatRepository {
    private final Map<ChatId , Chat> storage = new HashMap<>();
    @Override
    public Optional<Chat> findById(ChatId chatId) {
        return Optional.ofNullable(storage.get(chatId));
    }

    @Override
    public void save(Chat chat) {
        storage.put(chat.getChatId() , chat);
    }

    @Override
    public List<Chat> findAllByUserId(UserId userId) {
        return storage.values().stream().
                        filter(chat -> chat.involves(userId)).
                        toList();
    }

    @Override
    public Optional<Chat> findByUsersIds(UserId userA, UserId userB) {
        for (Chat value: storage.values()){
            if (value.involves(userA) && value.involves(userB)){
                return Optional.of(value);
            }
        }
        return Optional.empty();
        
    }

    @Override
    public void delete(ChatId chatId) {
        storage.remove(chatId);
    }
}
