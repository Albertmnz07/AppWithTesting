package domain.valueObject;

import domain.exceptions.MessageContentInvalidException;

public class MessageContent {

    private final String content;
    public static final int MAX_LENGTH = 1000;
    public static final String TOO_LONG_ERROR = "The message is too long";
    public static final String NOT_EMPTY_ERROR = "The message must not be empty";


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

    public String getValue(){
        return this.content;
    }



}
