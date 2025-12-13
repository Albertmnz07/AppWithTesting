package main.domain.repositories;

import main.domain.entities.Chat;
import main.domain.valueObject.ChatId;
import main.domain.valueObject.UserId;

import java.util.List;
import java.util.Optional;

public interface ChatRepository {
    Optional<Chat> findById(ChatId chatId);
    void save(Chat chat);
    List<Chat> findAllByUserId(UserId userId); //find all the users chats
    void delete(ChatId chatId);
}
