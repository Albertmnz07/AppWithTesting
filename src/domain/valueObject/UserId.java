package domain.valueObject;

import domain.exceptions.IdEmptyException;

import java.util.UUID;

public class UserId {

    public static final String USERID_NULL_ERROR = "ID can not be null";

    private final UUID userId;

    public UserId(){
        this.userId = UUID.randomUUID();
    }

    public UUID getValue(){
        return userId;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;
        UserId possible = (UserId) obj;
        return (this.userId.equals(possible.userId));
    }

}
