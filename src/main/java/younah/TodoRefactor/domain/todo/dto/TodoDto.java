//package younah.TodoRefactor.domain.todo.dto;
//
//import lombok.Getter;
//
//@Getter
//public class TodoDto {
//    String content;
//}
package younah.TodoRefactor.domain.todo.dto;

import younah.TodoRefactor.domain.todo.entity.Todo;

public record TodoDto (
        Long id,
        String content,
        Todo.TodoStatus status
) {
    public static TodoDto fromEntity(Todo todo) {
        return new TodoDto(
                todo.getId(),
                todo.getContent(),
                todo.getStatus()
        );
    }
}