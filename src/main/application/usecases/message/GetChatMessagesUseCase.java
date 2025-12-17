package main.application.usecases.message;

import main.application.exceptions.ChatNotFoundException;
import main.domain.entities.Chat;
import main.domain.entities.Message;
import main.domain.repositories.ChatRepository;
import main.domain.repositories.MessageRepository;
import main.domain.valueObject.ChatId;

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
