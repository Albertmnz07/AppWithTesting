package domain.valueObject;

import java.util.UUID;


/**
 * <p>Represents a user's id inside the system</p>
 * <p>This object is immutable</p>
 */
public class UserId extends Identifier {

    /**
     * Create a new {@code UserId}
     * @param value the id in his primitive form({@link UUID})
     */
    public UserId(UUID value){
        super(value);
    }

    /**
     * Generates and return a new random {@link UserId}
     * @return id in his Value Object form({@link UUID}
     */
    public static UserId generate(){
        return new UserId(generateValue());
    }

}
