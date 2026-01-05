package AppPro.application.usecases.message;

import AppPro.domain.entities.Chat;
import AppPro.domain.entities.Message;
import AppPro.domain.exceptions.chat.ChatNotFoundException;
import AppPro.domain.repositories.ChatRepository;
import AppPro.domain.repositories.MessageRepository;
import AppPro.domain.valueObject.ChatId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetChatMessagesUseCase {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    public GetChatMessagesUseCase(MessageRepository messageRepository , ChatRepository chatRepository){
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
    }

    public List<Message> execute(ChatId chatId){

        Chat chat = chatRepository.findById(chatId).orElseThrow(ChatNotFoundException::new);
        List<Message> messageList = messageRepository.findByChatId(chatId);

        return messageList;
    }
}
