package main.domain.entities;

import main.domain.valueObject.Password;
import main.domain.valueObject.UserId;
import main.domain.valueObject.UserName;

public class User {
	
	private UserName userName;
    private Password password;
	private final UserId userId;
	
	public User(UserName userName , Password password , UserId userId)  {

        this.userName = userName;
        this.password = password;
        this.userId = userId;

	}

    public UserName getUserName(){
        return this.userName;
    }

    public Password getPassword(){
        return this.password;
    }

    public UserId getUserId(){
        return this.userId;
    }

    public void changeUserName(UserName newUserName){
        this.userName = newUserName;
    }

    public void changePassword(Password newPassword){
        this.password = newPassword;
    }

    public boolean passwordMatches(Password candidate){
        return this.password.equals(candidate);
    }

    public boolean hasId(UserId userId){
        return this.userId.equals(userId);
    }

    @Override
    public boolean equals(Object object){
        if (this == object) return true;
        if (object.getClass() != getClass()) return false;
        User possibleDifferent = (User) object;
        return userId.equals(possibleDifferent.getUserId());
    }

}
