package main.infrastructure.input.cli;

import main.application.usecases.chat.CreateChatUseCase;
import main.application.usecases.chat.GetUserChatsUseCase;
import main.application.usecases.message.GetChatMessagesUseCase;
import main.application.usecases.message.SendMessageUseCase;
import main.application.usecases.user.CreateUserUseCase;
import main.application.usecases.user.LogInUseCase;
import main.domain.entities.User;
import main.domain.repositories.ChatRepository;
import main.domain.repositories.MessageRepository;
import main.domain.repositories.UserRepository;
import main.infrastructure.persistence.inmemory.FakeChatRepository;
import main.infrastructure.persistence.inmemory.FakeMessageRepository;
import main.infrastructure.persistence.inmemory.FakeUserRepository;

public class Main {


    public static void main(String[] args) {
        User currentUser;
        MessageRepository messageRepository = new FakeMessageRepository();
        UserRepository userRepository = new FakeUserRepository();
        ChatRepository chatRepository = new FakeChatRepository();

        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userRepository);
        LogInUseCase logInUseCase = new LogInUseCase(userRepository);

        SendMessageUseCase sendMessageUseCase = new SendMessageUseCase(messageRepository, chatRepository);
        GetChatMessagesUseCase getChatMessagesUseCase = new GetChatMessagesUseCase(messageRepository, chatRepository);

        CreateChatUseCase createChatUseCase = new CreateChatUseCase(chatRepository);
        GetUserChatsUseCase getUserChatsUseCase = new GetUserChatsUseCase(chatRepository);

        var runner = new ConsoleRunner(createUserUseCase , logInUseCase , sendMessageUseCase , getChatMessagesUseCase
        , createChatUseCase , getUserChatsUseCase);
    }

}
