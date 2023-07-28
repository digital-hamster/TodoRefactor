package younah.TodoRefactor.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

    TODO_NOT_EXSIST("존재하지 않는 Todo 입니다.");

    private String message;
}
