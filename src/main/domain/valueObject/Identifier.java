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

    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;
        Identifier possible = (Identifier) obj;
        return possible.equals(this.value);
    }

    public static UUID generate(){
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