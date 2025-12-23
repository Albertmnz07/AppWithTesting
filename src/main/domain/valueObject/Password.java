package main.domain.valueObject;

import main.domain.exceptions.PasswordEmptyException;
import main.domain.exceptions.PasswordTooLongException;
import main.domain.exceptions.PasswordTooShortException;

import java.util.Objects;

/**
 * <p>Represents a password inside the system</p>
 * <p>This value object is immutable and guarantees this business rule</p>
 *
 * <ul>
 *     <li>The length must be between {@value MIN_LENGTH} and {@value MAX_LENGTH}</li>
 *
 * </ul>
 */

public class Password {

    /**
     * Maximum length in characters
     */
    public static final int MAX_LENGTH = 10;
    /**
     * Minimum length in characters
     */
    public static final int MIN_LENGTH = 2;

    /**
     * Error message thrown when password is too short
     */
    public static final String ERROR_TOO_SHORT = "Password too short";
    /**
     * Error message thrown when username is too long
     */
    public static final String ERROR_TOO_LONG = "Password too long";

	private final String value;

    /**
     * Create a new {@code Password}
     *
     * Constructor validates the size of the password and creates it.
     * @param value The password in his primitive value(String)
     * @throws PasswordTooShortException if length is under the minimum allowed
     * @throws PasswordTooLongException if length is over the maximum allowed
     * @throws PasswordEmptyException if the value is empty
     */
	public Password(String value) {

        if(value.isEmpty()){
            throw new PasswordEmptyException();
        }

        if (value.length() < MIN_LENGTH){
            throw new PasswordTooShortException();
        }

        if (value.length() > MAX_LENGTH){
            throw new PasswordTooLongException();
        }
		this.value = value;
	}

    /**
     * Return the password value
     * @return password value in primitive form(String)
     */

	public String getValue() {
		return value;
	}

    /**
     * Compares the object with another
     * Two passwords are equals if his primitive value({@link String}) is the same
     * @param obj   the reference object with which to compare.
     * @return true if are equals, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;
        Password possibleEqual = (Password) obj;
        return this.getValue().equals(possibleEqual.getValue());
    }

    /**
     * Return the hashcode for its username
     * @return The hashcode
     */
     @Override
    public int hashCode(){return Objects.hash(this.value);}

}
