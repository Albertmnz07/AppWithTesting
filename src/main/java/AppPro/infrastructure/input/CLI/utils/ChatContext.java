package AppPro.infrastructure.input.CLI.utils;

import AppPro.domain.entities.User;
import AppPro.domain.valueObject.ChatId;
import org.springframework.stereotype.Service;

@Service
public class ChatContext {
    private User currentUser;
    private ChatId currentChatId;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public ChatId getCurrentChatId() {
        return currentChatId;
    }

    public void setCurrentChatId(ChatId currentChatId) {
        this.currentChatId = currentChatId;
    }
}
