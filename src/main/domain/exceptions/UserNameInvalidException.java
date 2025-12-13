package main.domain.exceptions;

public class UserNameInvalidException extends RuntimeException {

	public UserNameInvalidException(String message) {
		super(message);
	}

}
