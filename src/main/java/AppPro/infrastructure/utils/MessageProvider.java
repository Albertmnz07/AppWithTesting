package AppPro.infrastructure.utils;

import AppPro.domain.exceptions.DomainException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageProvider {

    public Locale currentLocale = Locale.of("en");
    private final MessageSource messageSource;

    public MessageProvider(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    public Locale getCurrentLocale(){
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public String getError(DomainException code){
        return messageSource.getMessage(code.getCode().toString() , null , getCurrentLocale());
    }

    public String getMessage(String text , Object... args){
        return messageSource.getMessage(text , args , getCurrentLocale());
    }
}
