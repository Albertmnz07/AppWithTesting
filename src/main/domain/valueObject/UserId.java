package main.domain.valueObject;

import main.domain.entities.User;

import java.util.UUID;

public class UserId {

    public static final String USER_ID_NULL_ERROR = "ID can not be null";

    private final UUID userId;

    public UserId(UUID value){
        this.userId = value;
    }

    public UUID getValue(){
        return userId;
    }

    public static UserId generate(){
        return new UserId(UUID.randomUUID());
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;
        UserId possible = (UserId) obj;
        return (this.userId.equals(possible.userId));
    }

}
