package main.java.application.usecases.chat;

import main.java.domain.entities.Chat;
import main.java.domain.repositories.ChatRepository;
import main.java.domain.valueObject.UserId;

import java.util.List;

public class GetUserChatsUseCase {

    private final ChatRepository chatRepository;

    public GetUserChatsUseCase(ChatRepository chatRepository){
        this.chatRepository = chatRepository;
    }

    public List<Chat> execute(UserId userId){

        List<Chat> list = chatRepository.findAllByUserId(userId);

        return list;
    }
}
