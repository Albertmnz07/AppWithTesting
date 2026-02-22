package AppPro.infrastructure.persistence.inmemory;

import AppPro.domain.entities.User;
import AppPro.domain.repositories.ChatRepository;
import AppPro.domain.repositories.MessageRepository;
import AppPro.domain.repositories.UserRepository;
import AppPro.domain.valueObject.Password;
import AppPro.domain.valueObject.UserId;
import AppPro.domain.valueObject.UserName;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    public DataInitializer(UserRepository userRepository, ChatRepository chatRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        userRepository.save(new User(new UserName("Al"), new Password("123"), UserId.generate()));
        userRepository.save(new User(new UserName("Alba"), new Password("123"), UserId.generate()));
        userRepository.save(new User(new UserName("Albert"), new Password("123"), UserId.generate()));
        userRepository.save(new User(new UserName("Alberto"), new Password("123"), UserId.generate()));
        userRepository.save(new User(new UserName("Alejandro"), new Password("123"), UserId.generate()));

        userRepository.save(new User(new UserName("Ana"), new Password("123"), UserId.generate()));
        userRepository.save(new User(new UserName("Anabel"), new Password("123"), UserId.generate()));
        userRepository.save(new User(new UserName("Antonio"), new Password("123"), UserId.generate()));

        userRepository.save(new User(new UserName("Bo"), new Password("123"), UserId.generate()));
        userRepository.save(new User(new UserName("Bea"), new Password("123"), UserId.generate()));
        userRepository.save(new User(new UserName("Belén"), new Password("123"), UserId.generate()));

        userRepository.save(new User(new UserName("Carlos"), new Password("123"), UserId.generate()));
        userRepository.save(new User(new UserName("Cris"), new Password("123"), UserId.generate()));
        userRepository.save(new User(new UserName("David"), new Password("123"), UserId.generate()));
        userRepository.save(new User(new UserName("Dan"), new Password("123"), UserId.generate()));
        userRepository.save(new User(new UserName("Elena"), new Password("123"), UserId.generate()));
        userRepository.save(new User(new UserName("Edu"), new Password("123"), UserId.generate()));

    }
}
