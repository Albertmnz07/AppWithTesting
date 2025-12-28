package main.infrastructure.utils;

import main.domain.error.ErrorCode;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageProvider {

    public static final Locale currentLocale = Locale.getDefault();

    private static final String ERROR_BUNDLE = "error.i18n";
    private static final String MESSAGE_BUNDLE = "message.i18n";

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
        return pattern;
    }

    public static String getMessage(String text){
        String pattern = getRawText(MESSAGE_BUNDLE , text);
        return pattern;
    }
}
