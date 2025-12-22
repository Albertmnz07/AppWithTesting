package main.domain.valueObject;

import main.domain.exceptions.IdEmptyException;

import java.util.Objects;
import java.util.UUID;

/**
 * Abstract class for identifiers in the domain.
 *
 * <p>A {@code Identifier}  wraps a {@link UUID} and provides:</p>
 *
 * <ul>
 *     <li>Immutability</li>
 *     <li>Non null-guarantees</li>
 *     <li>Value-based equality</li>
 * </ul>
 */

public abstract class Identifier {

    /**
     * Primitive value for Identifier
     */
    private final UUID value;

    /**
     * Creates a new identifier from a {@link UUID}
     * @param value primitive value for identifier
     * @throws IdEmptyException if id is empty or null
     */
    protected Identifier(UUID value){
        ensureValue(value);
        this.value = value;
    }

    /**
     * Ensures the id provided to constructor is correct.
     * @param id the primitive id value
     */
    private void ensureValue(UUID id){
        if (id == null){
            throw new IdEmptyException();
        }
    }

    /**
     * @return the primitive value of identifier
     */
    public UUID getValue(){
        return value;
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
        Identifier possible = (Identifier) obj;
        return possible.value.equals(this.value);
    }

    /**
     * Generates a new random {@link UUID} value
     * <p>
     *     Is used for child classes to create new identifiers
     * </p>
     * @return
     */
    protected static UUID generateValue(){
        return UUID.randomUUID();
    }

    /**
     *
     * @return the String representation of this identifier
     */
    @Override
    public String toString(){
        return this.value.toString();
    }

    /**
     *
     * @return the hash code of this identifier
     */
    @Override
    public int hashCode(){
        return Objects.hash(value);
    }
}