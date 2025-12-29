package main.java.application.usecases.chat;

import main.java.domain.entities.Chat;
import main.java.domain.exceptions.chat.ChatAlreadyExistsException;
import main.java.domain.repositories.ChatRepository;
import main.java.domain.valueObject.ChatId;
import main.java.domain.valueObject.UserId;

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
