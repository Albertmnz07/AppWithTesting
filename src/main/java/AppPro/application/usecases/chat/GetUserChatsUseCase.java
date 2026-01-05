package AppPro.application.usecases.chat;

import AppPro.domain.entities.Chat;
import AppPro.domain.repositories.ChatRepository;
import AppPro.domain.valueObject.UserId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
