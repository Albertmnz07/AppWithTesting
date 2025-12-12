package domain.entities;

import domain.valueObject.ChatId;
import domain.valueObject.MessageContent;
import domain.valueObject.MessageId;
import domain.valueObject.UserId;

public class Message {

    private final UserId senderId;
    private final MessageId messageID;
    private MessageContent messageContent;
    private final ChatId chatID;

    public Message(UserId senderId , MessageId messageId , MessageContent messageContent
     , ChatId chatId) {
        this.senderId = senderId;
        this.messageID = messageId;
        this.messageContent = messageContent;
        this.chatID = chatId;
    }

    public UserId getSenderId(){
        return this.senderId;
    }

    public MessageId getMessageID() {
        return messageID;
    }

    public MessageContent getMessageContent(){
        return this.messageContent;
    }

    public ChatId getChatID(){
        return this.chatID;
    }

    public void updateMessageContent(MessageContent newMessageContent){
        this.messageContent = newMessageContent;
    }


}
