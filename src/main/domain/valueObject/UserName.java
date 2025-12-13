package main.domain.valueObject;

import main.domain.exceptions.UserNameInvalidException;

public class UserName {

	public static final int MAX_LENGTH = 10;
	public static final int MIN_LENGTH = 2;

	public static final String ERROR_TOO_SHORT = "Name too short";
	public static final String ERROR_TOO_LONG = "Name too long";


	private final String value;

	public UserName(String value) {
		value = value.trim();
		if(value.length() < MIN_LENGTH) {
			throw new UserNameInvalidException(ERROR_TOO_SHORT);
		}

		if (value.length() > MAX_LENGTH) {
			throw new UserNameInvalidException(ERROR_TOO_LONG);
		}

		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object obj){
		if (obj == null) return false;
		if (obj.getClass() != getClass()) return false;
		UserName possible = (UserName) obj;
		return (this.value.equals(possible.getValue()));
	}


}
