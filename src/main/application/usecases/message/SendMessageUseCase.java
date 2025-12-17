package main.application.usecases.message;

import main.application.exceptions.ChatNotFoundException;
import main.application.exceptions.UserNotParticipantInChat;
import main.domain.entities.Chat;
import main.domain.entities.Message;
import main.domain.repositories.ChatRepository;
import main.domain.repositories.MessageRepository;
import main.domain.valueObject.ChatId;
import main.domain.valueObject.MessageContent;
import main.domain.valueObject.MessageId;
import main.domain.valueObject.UserId;

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
