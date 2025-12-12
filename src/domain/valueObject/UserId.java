package domain.valueObject;

import domain.exceptions.IdEmptyException;

import java.util.UUID;

public class UserId {

    public static final String USERID_NULL_ERROR = "ID can not be null";

    private final UUID userId;

    public UserId(UUID userId){

        if (userId == null){
            throw new IdEmptyException(USERID_NULL_ERROR);
        }

        this.userId = userId;
    }

    public UUID getValue(){
        return userId;
    }

}
