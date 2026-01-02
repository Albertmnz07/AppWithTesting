package application.usecases.chat;

import domain.entities.Chat;
import domain.exceptions.chat.ChatAlreadyExistsException;
import domain.repositories.ChatRepository;
import domain.valueObject.ChatId;
import domain.valueObject.UserId;
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
