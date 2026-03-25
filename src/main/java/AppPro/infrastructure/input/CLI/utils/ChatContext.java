package AppPro.infrastructure.input.CLI.utils;

import AppPro.domain.entities.User;
import org.springframework.stereotype.Service;

@Service
public class ChatContext {
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
