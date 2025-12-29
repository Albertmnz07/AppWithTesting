package main.java.infrastructure.utils;

import main.java.domain.error.ErrorCode;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageProvider {

    public static Locale currentLocale = Locale.getDefault();

    private static final String ERROR_BUNDLE = "i18n.error";
    private static final String MESSAGE_BUNDLE = "i18n.message";

    /**
     * Obtains a text in the correct language. Is independent of errors or simple plain text.
     * This function exists because calling other functions will be easier separating responsibilities.
     * @param bundleName Defines the type of text(error, simple text, etc)
     * @param key the code of the text that is required
     * @return the {@link String} ready to be displayed
     */
    public static String getRawText(String bundleName , String key){
        try{
            ResourceBundle bundle = ResourceBundle.getBundle(bundleName , currentLocale);
            return bundle.getString(key);
        } catch (Exception e){
            return "Missing key: " + key;
        }
    }

    public static String getError(ErrorCode code){
        String pattern = getRawText(ERROR_BUNDLE , code.toString());
        return pattern; //is expected to add an implementation to include variables
    }

    public static String getMessage(String text){
        String pattern = getRawText(MESSAGE_BUNDLE , text);
        return pattern;
    }
}
