package domain.repositories;

import domain.entities.Chat;
import domain.valueObject.ChatId;
import domain.valueObject.UserId;

import java.util.List;
import java.util.Optional;

public interface ChatRepositorie {
    Optional<Chat> findById(ChatId chatId);
    void save(Chat chat);
    List<Chat> findAllByUserId(UserId userId); //find all the users chats
    void delete(ChatId chatId);
}
