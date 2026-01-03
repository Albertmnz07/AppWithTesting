package infrastructure.input.CLI;

import application.ports.InputPort;
import application.usecases.chat.CreateChatUseCase;
import application.usecases.chat.GetUserChatsUseCase;
import application.usecases.message.GetChatMessagesUseCase;
import application.usecases.message.SendMessageUseCase;
import application.usecases.user.CreateUserUseCase;
import application.usecases.user.FindUserByUserNameUseCase;
import application.usecases.user.LogInUseCase;
import domain.repositories.ChatRepository;
import domain.repositories.MessageRepository;
import domain.repositories.UserRepository;
import infrastructure.input.CLI.utils.InputReader;
import infrastructure.persistence.inmemory.FakeChatRepository;
import infrastructure.persistence.inmemory.FakeMessageRepository;
import infrastructure.persistence.inmemory.FakeUserRepository;
import infrastructure.utils.MessageProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {


    public static void main(String[] args) {

        SpringApplication.run(Main.class , args);
    }

}
