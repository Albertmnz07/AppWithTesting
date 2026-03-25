package AppPro.application.usecases.chat;

import AppPro.domain.entities.Chat;
import AppPro.domain.exceptions.chat.ChatAlreadyExistsException;
import AppPro.domain.repositories.ChatRepository;
import AppPro.domain.valueObject.ChatId;
import AppPro.domain.valueObject.UserId;
import org.springframework.stereotype.Service;

@Service
public class GetOrCreateChatUseCase {

    ChatRepository chatRepository;

    public GetOrCreateChatUseCase(ChatRepository chatRepository){
        this.chatRepository = chatRepository;
    }

    public Chat execute(UserId userA , UserId userB){
        var existingChat = chatRepository.findByUsersIds(userA , userB);

        if (existingChat.isPresent()){
            return existingChat.get();
        }

        Chat newChat = new Chat(userA, userB, ChatId.generate());
        chatRepository.save(newChat);

        return newChat;

    }
}
