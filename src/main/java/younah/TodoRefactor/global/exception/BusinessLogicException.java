package younah.TodoRefactor.global.exception;

import lombok.Getter;
@Getter
public class BusinessLogicException extends RuntimeException {
    private ExceptionCode exceptionCode;

    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode; // 얘 없으면 예외 메시지 제대로 안나옴 ㅎㅎ..헤헤.
    }
}
