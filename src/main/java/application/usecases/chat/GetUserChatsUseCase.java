package application.usecases.chat;

import domain.entities.Chat;
import domain.repositories.ChatRepository;
import domain.valueObject.UserId;

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
