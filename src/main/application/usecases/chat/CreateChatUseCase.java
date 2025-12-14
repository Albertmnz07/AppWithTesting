package main.application.usecases.chat;

import main.application.exceptions.ChatAlreadyExistsException;
import main.application.usecases.user.CreateUserUseCase;
import main.domain.entities.Chat;
import main.domain.entities.User;
import main.domain.repositories.ChatRepository;
import main.domain.valueObject.ChatId;
import main.domain.valueObject.UserId;

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
