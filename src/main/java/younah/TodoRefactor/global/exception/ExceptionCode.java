package younah.TodoRefactor.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

    TODO_NOT_EXSIST(301, "존재하지 않는 Todo 입니다.");


    private int code;
    private String message;
}
