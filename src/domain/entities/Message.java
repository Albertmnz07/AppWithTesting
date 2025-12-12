package domain.entities;

import domain.valueObject.ChatID;
import domain.valueObject.MessageContent;
import domain.valueObject.MessageID;
import domain.valueObject.UserId;

public class Message {

    private final UserId senderId;
    private final MessageID messageID;
    private MessageContent messageContent;
    private final ChatID chatID;

    public Message(UserId senderId , MessageID messageID , MessageContent messageContent
     , ChatID chatID) {
        this.senderId = senderId;
        this.messageID = messageID;
        this.messageContent = messageContent;
        this.chatID = chatID;
    }

    public UserId getSenderId(){
        return this.senderId;
    }

    public MessageID getMessageID() {
        return messageID;
    }

    public MessageContent getMessageContent(){
        return this.messageContent;
    }

    public ChatID getChatID(){
        return this.chatID;
    }

    public void updateMessageContent(MessageContent newMessageContent){
        this.messageContent = newMessageContent;
    }


}
