package main.java.domain.valueObject;

import main.java.domain.exceptions.message.MessageEmptyException;
import main.java.domain.exceptions.message.MessageTooLongException;

/**
 * <p>Represents the content of a message inside the system</p>
 * <p>This value object is immutable and guarantees this business rules</p>
 * <ul>
 *     <li>The length can't be over 1000 characters</li>
 *     <li>Content cannot be empty or be contained only by whitespace. To validate it constructor uses {@link String#trim()}</li>
 * </ul>
 */
public class MessageContent {

    /**
     * Maximum length in characters
     */
    public static final int MAX_LENGTH = 1000;

    private final String content;

    /**
     * Creates a new {@code MessageContent}
     * <p>The input is normalized using {@link String#trim()}</p>
     * Constructor validates the size and creates it
     * @param content The content of the message in his primitive value({@link String})
     * @throws MessageTooLongException if normalized value is over 1000 characters
     * @throws MessageEmptyException if normalized value is empty
     */
    public MessageContent(String content){
        content = content.trim();

        if (content.length() > MAX_LENGTH){
            throw new MessageTooLongException();
        }

        if (content.isEmpty()){
            throw new MessageEmptyException();
        }
        this.content = content;
    }

    /**
     * Return the message content value
     * @return content of the message in primitive form({@link String})
     */
    public String getValue(){
        return this.content;
    }

    /**
     * Compares the object with another.
     * Two UserName are equals if his primitive value(String) is the same.
     * @param obj the reference object with which to compare.
     * @return true if are equals, false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;
        MessageContent possible = (MessageContent) obj;
        return (this.content.equals(possible.getValue()));
    }

}
