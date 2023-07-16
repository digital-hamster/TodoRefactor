package younah.TodoRefactor.domain.todo.response;

import lombok.Getter;
import younah.TodoRefactor.domain.todo.entity.Todo;

import java.time.LocalDateTime;

@Getter
public class TodoResponse {
    long todoId;
    Todo.TodoStatus todoStatus;
    String content;


    LocalDateTime createdAt;
    LocalDateTime modifiedAt;

    public static TodoResponse fromTodo(Todo todo){ //명확한 의미전달 ...
        TodoResponse response = new TodoResponse();

        response.todoId = todo.getId();
        response.todoStatus = todo.getStatus();
        response.content = todo.getContent();
        response.createdAt = todo.getCreatedAt();
        response.modifiedAt = todo.getModifiedAt();

        return response;
    }
}
