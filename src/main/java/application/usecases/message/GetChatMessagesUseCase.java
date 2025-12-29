package application.usecases.message;

import domain.entities.Chat;
import domain.entities.Message;
import domain.exceptions.chat.ChatNotFoundException;
import domain.repositories.ChatRepository;
import domain.repositories.MessageRepository;
import domain.valueObject.ChatId;

import java.util.List;

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
