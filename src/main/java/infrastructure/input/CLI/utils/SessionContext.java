package infrastructure.input.CLI.utils;

import domain.entities.User;
import org.springframework.stereotype.Component;

@Component
public class SessionContext {
    private User currentUser;

    public User getCurrentUser(){
        return currentUser;
    }

    public void setUser(User newUser){
        this.currentUser = newUser;
    }
}
