package AppPro.infrastructure.utils;

import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.DomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageProviderTest {

    @Mock
    MessageSource messageSource;

    @InjectMocks
    MessageProvider messageProvider;

    @BeforeEach
    void setUp(){
        Locale.setDefault(new Locale("en"));
    }

    @Test
    void shouldReturnErrorMessage(){
        DomainException exception = mock(DomainException.class);
        when(exception.getCode()).thenReturn(ErrorCode.USER_NOT_FOUND);

        String message = ErrorCode.USER_NOT_FOUND.name();

        when(messageSource.getMessage(ErrorCode.USER_NOT_FOUND.name()
                , null , Locale.getDefault())).thenReturn(message);

        String result = messageProvider.getError(exception);

        assertEquals(message , result);
    }

}