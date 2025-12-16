package main.domain.valueObject;

import java.util.UUID;

/**
 * <p>Represents an user's id inside the system</p>
 * <p>This object is immutable</p>
 *
 */
public class UserId {

    private final UUID userId;

    /**
     * Create a new {@code UserId}
     * @param value the id in his primitive form({@link UUID})
     */
    public UserId(UUID value){
        this.userId = value;
    }

    /**
     * Returns the id value
     * @return id value in his primitive form({@link UUID)}
     */
    public UUID getValue(){
        return userId;
    }

    /**
     * Generates and return a new random {@link UserId}
     * @return id in his Value Object form({@link UserId}
     */
    public static UserId generate(){
        return new UserId(UUID.randomUUID());
    }

    /**
     * Compares the object with another
     * Two ids are equal if his primitive value({@link UUID}) is the same
     * @param obj   the reference object with which to compare.
     * @return true if are equals, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;
        UserId possible = (UserId) obj;
        return (this.userId.equals(possible.userId));
    }

}
