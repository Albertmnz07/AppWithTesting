package domain.entities;

import domain.valueObject.ChatId;
import domain.valueObject.MessageContent;
import domain.valueObject.MessageId;
import domain.valueObject.UserId;

/**
 * Represents a message sent by a user within a chat
 *
 * <p>A message is a domain entity uniquely identified by its {@link MessageId}.
 * It belongs to a specific chat and has a sender, represented by {@link UserId}.
 * The content of the message can be updated, but the id and sender are uniquely.</p>
 *
 * <p>Domain invariants:
 * <ul>
 *     <li>A message has always a unique sender</li>
 *     <li>A message has always a unique and immutable id</li>
 *     <li>A message has a non-null and non-empty, mutable content</li>
 * </ul>
 * </p>
 */
public class Message {

    private final UserId senderId;
    private final MessageId messageID;
    private MessageContent messageContent;
    private final ChatId chatID;

    /**
     * Creates a new message
     * @param senderId unique identifier of the user who sends the message
     * @param messageId unique identifier of the message
     * @param messageContent variable content of message
     * @param chatId unique identifier of the chats where message is sent
     */
    public Message(UserId senderId , MessageId messageId , MessageContent messageContent
     , ChatId chatId) {
        this.senderId = senderId;
        this.messageID = messageId;
        this.messageContent = messageContent;
        this.chatID = chatId;
    }

    /**
     * Return the unique identifier of the user who sent the messages
     * @return the user identifier
     */
    public UserId getSenderId(){
        return this.senderId;
    }

    /**
     * Returns the unique identifier of the message
     * @return the message identifier
     */
    public MessageId getMessageID() {
        return messageID;
    }

    /**
     * Return the content of the message
     * @return the message content
     */
    public MessageContent getMessageContent(){
        return this.messageContent;
    }

    /**
     * Returns the identifier of the chat this message belongs to
     * @return the chat identifier
     */
    public ChatId getChatID(){
        return this.chatID;
    }

    /**
     * Updates the content of the message
     * @param newMessageContent the new content for the message
     */
    public void updateMessageContent(MessageContent newMessageContent){
        this.messageContent = newMessageContent;
    }


}
