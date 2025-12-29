package main.java.application.usecases.message;

import main.java.domain.entities.Chat;
import main.java.domain.entities.Message;
import main.java.domain.exceptions.chat.ChatNotFoundException;
import main.java.domain.repositories.ChatRepository;
import main.java.domain.repositories.MessageRepository;
import main.java.domain.valueObject.ChatId;

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
