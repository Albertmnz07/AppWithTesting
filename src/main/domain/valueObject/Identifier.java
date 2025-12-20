package main.domain.valueObject;

import main.domain.exceptions.IdEmptyException;

import java.util.Objects;
import java.util.UUID;

public abstract class Identifier {

    protected final UUID value;

    protected Identifier(UUID value){
        this.value = value;
    }

    private void ensureValue(UUID id){
        if (id == null || value.toString().trim().isEmpty()){
            throw new IdEmptyException();
        }
    }

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

    protected static UUID generateValue(){
        return UUID.randomUUID();
    }

    @Override
    public String toString(){
        return this.value.toString();
    }

    @Override
    public int hashCode(){
        return Objects.hash(value);
    }
}