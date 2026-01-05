package AppPro.domain.repositories;

import AppPro.domain.entities.Chat;
import AppPro.domain.valueObject.ChatId;
import AppPro.domain.valueObject.UserId;

import java.util.List;
import java.util.Optional;

public interface ChatRepository {
    Optional<Chat> findById(ChatId chatId);
    void save(Chat chat);
    List<Chat> findAllByUserId(UserId userId); //find all the users chats
    Optional<Chat> findByUsersIds(UserId userA , UserId userB);
    void delete(ChatId chatId);
}
