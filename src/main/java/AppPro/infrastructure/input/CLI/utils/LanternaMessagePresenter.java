package AppPro.infrastructure.input.CLI.utils;

import AppPro.domain.exceptions.DomainException;
import AppPro.infrastructure.input.CLI.adapters.ConsoleScrollingForm;
import AppPro.infrastructure.utils.MessageProvider;
import com.googlecode.lanterna.TextColor;
import org.springframework.stereotype.Service;

/**
 * Orchestrates the visual representation of messages and errors using colors.
 */
@Service
public class LanternaMessagePresenter {

    private final MessageProvider messageProvider;

    public LanternaMessagePresenter(MessageProvider messageProvider){
        this.messageProvider = messageProvider;
    }

    public void printError(ConsoleScrollingForm form , DomainException e){
        String localizedMessage = messageProvider.getError(e);
        form.printLine(localizedMessage , TextColor.ANSI.RED);
    }

    public void printInfo(ConsoleScrollingForm form , String key){
        String message = messageProvider.getMessage(key);
        form.printLine(message , TextColor.ANSI.GREEN);
    }
}
