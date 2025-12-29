package main.java.application.usecases.message;

import main.java.domain.exceptions.chat.ChatNotFoundException;
import main.java.domain.exceptions.user.UserNotParticipantInChat;
import main.java.domain.entities.Chat;
import main.java.domain.entities.Message;
import main.java.domain.repositories.ChatRepository;
import main.java.domain.repositories.MessageRepository;
import main.java.domain.valueObject.ChatId;
import main.java.domain.valueObject.MessageContent;
import main.java.domain.valueObject.MessageId;
import main.java.domain.valueObject.UserId;

public class SendMessageUseCase {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    public SendMessageUseCase(MessageRepository messageRepository , ChatRepository chatRepository){
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
    }

    public Message execute(ChatId chatId , UserId senderId , String messageContentStr){

        MessageContent messageContent = new MessageContent(messageContentStr);

        Chat chat = chatRepository.findById(chatId).orElseThrow(ChatNotFoundException::new);
        if (!chat.involves(senderId)) throw new UserNotParticipantInChat();

        MessageId messageId = MessageId.generate();

        Message message = new Message(senderId , messageId , messageContent , chatId);

        messageRepository.save(message);

        return message;
    }
}
