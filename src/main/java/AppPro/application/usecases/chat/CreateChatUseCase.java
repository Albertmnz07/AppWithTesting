package AppPro.application.usecases.chat;

import AppPro.domain.entities.Chat;
import AppPro.domain.exceptions.chat.ChatAlreadyExistsException;
import AppPro.domain.repositories.ChatRepository;
import AppPro.domain.valueObject.ChatId;
import AppPro.domain.valueObject.UserId;
import org.springframework.stereotype.Service;

@Service
public class CreateChatUseCase {

    ChatRepository chatRepository;

    public CreateChatUseCase(ChatRepository chatRepository){
        this.chatRepository = chatRepository;
    }

    public Chat execute(UserId userA , UserId userB){

        if (chatRepository.findByUsersIds(userA , userB).isPresent()){
            throw new ChatAlreadyExistsException();
        }

        ChatId chatId = ChatId.generate();

        Chat chat = new Chat(userA , userB , chatId);

        chatRepository.save(chat);

        return chat;

    }
}
