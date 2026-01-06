package AppPro.infrastructure.utils;

import AppPro.domain.exceptions.DomainException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageProvider {

    //public static Locale currentLocale = new Locale("es");
    private final MessageSource messageSource;

    public MessageProvider(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    private Locale getCurrentLocale(){
        return Locale.getDefault();
    }

    public String getError(DomainException code){
        return messageSource.getMessage(code.getCode().name() , null , getCurrentLocale());
    }

    public String getMessage(String text , Object... args){
        return messageSource.getMessage(text , args , getCurrentLocale());
    }
}
