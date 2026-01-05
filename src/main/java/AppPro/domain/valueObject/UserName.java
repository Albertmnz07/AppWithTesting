package AppPro.domain.valueObject;

import AppPro.domain.exceptions.user.UserNameEmptyException;
import AppPro.domain.exceptions.user.UserNameTooLongException;
import AppPro.domain.exceptions.user.UserNameTooShortException;

import java.util.Objects;

/**
 * <p>Represents a username inside the system.</p>
 * <p>This value object is immutable and guarantees this business rules during its construction</p>
 *
 * <ul>
 *     <li>The length must be between {@value MIN_LENGTH} and {@value MAX_LENGTH} characters</li>
 *     <li>Names can not be empty or be contained only whitespace. To validate it constructor uses {@link String#trim()}</li>
 * </ul>
 */

public class UserName {

	/**
	 * Maximum length in characters
	 */
	public static final int MAX_LENGTH = 10;
	/**
	 * Minimum length in characters
	 */
	public static final int MIN_LENGTH = 2;

	private final String value;

	/**
	 * Create a new {@code UserName}
	 *
	 * <p>
	 *     The input value is normalized using {@link String#trim()} before applying length validation rules.
	 * </p>
	 * Constructor that validates the size of the name and creates it.
	 * @param value The username in his primitive value(String).
	 * @throws UserNameTooShortException if normalized value is too short.
	 * @throws UserNameTooLongException if normalized value is too long
	 * @throws UserNameEmptyException if normalized value is empty
	 */

	public UserName(String value) {
		value = value.trim();

		if (value.isEmpty()){
			throw new UserNameEmptyException();
		}

		if(value.length() < MIN_LENGTH) {
			throw new UserNameTooShortException();
		}

		if (value.length() > MAX_LENGTH) {
			throw new UserNameTooLongException();
		}

		this.value = value;
	}

	/**
	 * Return the username value
	 * @return username value in primitive form(String)
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Compares the object with another.
	 * Two UserName are equals if his primitive value(String) is the same.
	 * @param obj the reference object with which to compare.
	 * @return true if are equals, false otherwise.
	 */
	@Override
	public boolean equals(Object obj){
		if (obj == null) return false;
		if (obj.getClass() != getClass()) return false;
		UserName possible = (UserName) obj;
		return (this.value.equals(possible.getValue()));
	}

	/**
	 * Return the hashcode for its username
	 * @return The hashcode
	 */

	@Override
	public int hashCode(){
		return Objects.hash(value);
	}


}