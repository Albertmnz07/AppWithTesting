package main.application.usecases.chat;

import main.domain.exceptions.chat.ChatAlreadyExistsException;
import main.domain.entities.Chat;
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
