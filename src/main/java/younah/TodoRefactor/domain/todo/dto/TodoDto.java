package younah.TodoRefactor.domain.todo.dto;
import younah.TodoRefactor.domain.todo.entity.Todo;

import java.time.LocalDateTime;

//컨트롤러 단에 값을 전달하기 위한 객체 (~> service)
//왜 이 레코드는 컨트롤러에 내부로 넣지 않고 빼놨을까 ... 서비스에서 공용으로 반환되는 Dto라서?

//반환되는 요소를 보면 응답하기 위해 필요한 모든 게 있음
//여기서 필요한 값을 뽑아서 response에 담나봄

public record TodoDto (
        Long id,
        String content,
        Todo.TodoStatus status,
        LocalDateTime deletedAt
){ //service -> controller ( Entity -> dto )
    public static  TodoDto fromEntity(Todo todo){
        return new TodoDto(
                todo.getId(),
                todo.getContent(),
                todo.getStatus(),
                todo.getDeletedAt()
        );
    }
}
