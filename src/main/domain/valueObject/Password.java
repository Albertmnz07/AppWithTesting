package main.domain.valueObject;

import main.domain.exceptions.PasswordInvalidException;

public class Password {

    public static final int MAX_LENGTH = 10;
    public static final int MIN_LENGTH = 2;

    public static final String ERROR_TOO_SHORT = "Password too short";
    public static final String ERROR_TOO_LONG = "Password too long";

	private String value;
	
	public Password(String value) {

        if (value.length() < MIN_LENGTH){
            throw new PasswordInvalidException(ERROR_TOO_SHORT);
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
        Password possibleEqual = (Password) obj;
        return this.getValue().equals(possibleEqual.getValue());
    }

}
