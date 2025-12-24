package main.domain.exceptions.user;

public class UserNameInvalidException extends RuntimeException {

	public UserNameInvalidException(String message) {
		super(message);
	}

}
