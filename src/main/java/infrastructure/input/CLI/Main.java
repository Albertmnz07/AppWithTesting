package infrastructure.input.CLI;

import application.ports.InputPort;
import application.usecases.chat.CreateChatUseCase;
import application.usecases.chat.GetUserChatsUseCase;
import application.usecases.message.GetChatMessagesUseCase;
import application.usecases.message.SendMessageUseCase;
import application.usecases.user.CreateUserUseCase;
import application.usecases.user.FindUserByUserNameUseCase;
import application.usecases.user.LogInUseCase;
import domain.entities.User;
import domain.repositories.ChatRepository;
import domain.repositories.MessageRepository;
import domain.repositories.UserRepository;
import infrastructure.input.CLI.utils.InputReader;
import infrastructure.persistence.inmemory.FakeChatRepository;
import infrastructure.persistence.inmemory.FakeMessageRepository;
import infrastructure.persistence.inmemory.FakeUserRepository;
import infrastructure.utils.MessageProvider;

public class Main {


    public static void main(String[] args) {
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
        MessageProvider messageProvider = new MessageProvider();

        var runner = new ConsoleRunner(createUserUseCase , logInUseCase , findUserByUserNameUseCase , sendMessageUseCase , getChatMessagesUseCase
        , createChatUseCase , getUserChatsUseCase , input , messageProvider);

        runner.run();
    }

}
