package main.java.infrastructure.input.CLI;

import main.java.application.ports.InputPort;
import main.java.application.usecases.chat.CreateChatUseCase;
import main.java.application.usecases.chat.GetUserChatsUseCase;
import main.java.application.usecases.message.GetChatMessagesUseCase;
import main.java.application.usecases.message.SendMessageUseCase;
import main.java.application.usecases.user.CreateUserUseCase;
import main.java.application.usecases.user.FindUserByUserNameUseCase;
import main.java.application.usecases.user.LogInUseCase;
import main.java.domain.entities.User;
import main.java.domain.repositories.ChatRepository;
import main.java.domain.repositories.MessageRepository;
import main.java.domain.repositories.UserRepository;
import main.java.infrastructure.input.CLI.utils.InputReader;
import main.java.infrastructure.persistence.inmemory.FakeChatRepository;
import main.java.infrastructure.persistence.inmemory.FakeMessageRepository;
import main.java.infrastructure.persistence.inmemory.FakeUserRepository;

public class Main {


    public static void main(String[] args) {
        User currentUser;
        MessageRepository messageRepository = new FakeMessageRepository();
        UserRepository userRepository = new FakeUserRepository();
        ChatRepository chatRepository = new FakeChatRepository();

        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userRepository);
        LogInUseCase logInUseCase = new LogInUseCase(userRepository);
        FindUserByUserNameUseCase findUserByUserNameUseCase = new FindUserByUserNameUseCase(userRepository);

        SendMessageUseCase sendMessageUseCase = new SendMessageUseCase(messageRepository, chatRepository);
        GetChatMessagesUseCase getChatMessagesUseCase = new GetChatMessagesUseCase(messageRepository, chatRepository);

        CreateChatUseCase createChatUseCase = new CreateChatUseCase(chatRepository);
        GetUserChatsUseCase getUserChatsUseCase = new GetUserChatsUseCase(chatRepository);

        InputPort input = new InputReader();

        var runner = new ConsoleRunner(createUserUseCase , logInUseCase , findUserByUserNameUseCase , sendMessageUseCase , getChatMessagesUseCase
        , createChatUseCase , getUserChatsUseCase , input);

        runner.run();
    }

}
