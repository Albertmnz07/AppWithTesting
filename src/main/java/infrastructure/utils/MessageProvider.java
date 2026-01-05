package infrastructure.utils;

import domain.exceptions.DomainException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

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

    /**
     * Obtains a text in the correct language. Is independent of errors or simple plain text.
     * This function exists because calling other functions will be easier separating responsibilities.
     * @param bundleName Defines the type of text(error, simple text, etc)
     * @param key the code of the text that is required
     * @return the {@link String} ready to be displayed
     */
    private String getRawText(String bundleName , String key){
        try{
            ResourceBundle bundle = ResourceBundle.getBundle(bundleName , currentLocale);
            return bundle.getString(key);
        } catch (Exception e){
            return "Missing key: " + key;
        }
    }

    public String getError(DomainException code){
        String pattern = getRawText(ERROR_BUNDLE , code.getCode().toString());
        return pattern; //is expected to add an implementation to include variables
    }

    public String getMessage(String text){
        String pattern = getRawText(MESSAGE_BUNDLE , text);
        return pattern;
    }
}
