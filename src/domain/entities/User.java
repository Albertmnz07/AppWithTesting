package domain.entities;

import domain.valueObject.UserId;
import domain.valueObject.UserName;

public class User {
	
	private UserName userName;
	private UserId id;
	
	public User(String userName , String password) {

        this.userName = new UserName(userName);

	}

}
