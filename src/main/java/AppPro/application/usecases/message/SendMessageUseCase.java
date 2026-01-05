package AppPro.application.usecases.message;

import AppPro.domain.entities.Chat;
import AppPro.domain.entities.Message;
import AppPro.domain.exceptions.chat.ChatNotFoundException;
import AppPro.domain.exceptions.user.UserNotParticipantInChat;
import AppPro.domain.repositories.ChatRepository;
import AppPro.domain.repositories.MessageRepository;
import AppPro.domain.valueObject.ChatId;
import AppPro.domain.valueObject.MessageContent;
import AppPro.domain.valueObject.MessageId;
import AppPro.domain.valueObject.UserId;
import org.springframework.stereotype.Service;

@Service
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
