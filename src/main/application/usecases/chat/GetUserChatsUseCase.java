package main.application.usecases.chat;

import main.domain.entities.Chat;
import main.domain.repositories.ChatRepository;
import main.domain.valueObject.UserId;

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
