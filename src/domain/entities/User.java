package domain.entities;

import domain.valueObject.UserName;

public class User {
	
	private UserName userName;
	private int id;
	
	public User(String userName , String password) {
		this.userName = new UserName(userName);
	}

}
