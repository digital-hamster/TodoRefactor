package younah.TodoRefactor.global.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import younah.TodoRefactor.global.exception.BusinessLogicException;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<String> handleException(BusinessLogicException ex){

        ResponseEntity<String> response = ResponseEntity
                .status(ex.getExceptionCode().getCode())
                .body(ex.getExceptionCode().getMessage());

    return response;
    }
}
