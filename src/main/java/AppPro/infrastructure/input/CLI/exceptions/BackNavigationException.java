package AppPro.infrastructure.input.CLI.exceptions;

/**
 * Exception when user want navigate back. It's an infrastructure exception level.
 */

public class BackNavigationException extends RuntimeException{

    public BackNavigationException(){
        super();
    }
}
