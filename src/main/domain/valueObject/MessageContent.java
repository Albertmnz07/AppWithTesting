package main.domain.valueObject;

import main.domain.exceptions.MessageContentInvalidException;

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

    /**
     * Error message thrown when the message is too long
     */
    public static final String TOO_LONG_ERROR = "The message is too long";

    /**
     * Error message thrown when the message is empty
     */
    public static final String NOT_EMPTY_ERROR = "The message must not be empty";

    private final String content;

    /**
     * Creates a new {@code MessageContent}
     * <p>The input is normalized using {@link String#trim()}</p>
     * Constructor validates the size and creates it
     * @param content The content of the message in his primitive value({@link String})
     * @throws MessageContentInvalidException if normalized value is over 1000 characters or is empty
     */
    public MessageContent(String content){
        content = content.trim();

        if (content.length() > MAX_LENGTH){
            throw new MessageContentInvalidException(TOO_LONG_ERROR);
        }

        if (content.isEmpty()){
            throw new MessageContentInvalidException(NOT_EMPTY_ERROR);
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
        UserName possible = (UserName) obj;
        return (this.content.equals(possible.getValue()));
    }

}
