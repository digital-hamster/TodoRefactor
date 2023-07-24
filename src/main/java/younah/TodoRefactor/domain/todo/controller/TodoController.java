package younah.TodoRefactor.domain.todo.controller;

import younah.TodoRefactor.domain.todo.entity.Todo;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import younah.TodoRefactor.domain.todo.service.TodoService;

//TODO 멘토님 저 질문있어요 !!!!!
//1. 레코드로 선언된 DTO는 서비스 단으로 인자가 넘겨져도 괜찮은 건가요? (31번 라인, 59번 라인)
//2. 레코드를 사용하기 위해 Controller에 Entity 코드가 import 되어도 되는 건가요..? (5번 라인)

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@Validated
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    TodoResponse createTodo(@NotNull @RequestBody TodoDto todoDto){
        String content = todoDto.content();
        TodoResponse response = todoService.createTodo(content);

        return response;
    }

    @GetMapping
    List<TodoResponse> getTodos(){
        return todoService.getTodos();
    }

    @GetMapping("/{todoId}")
    TodoResponse getTodo(@PathVariable long todoId){
        return todoService.getTodo(todoId);
    }

    @PatchMapping("/complete/{todoId}")
    void todoDone(@PathVariable long todoId){
        todoService.complete(todoId);
    }

    @PatchMapping("/setback/{todoId}")
    void todoWithDraw(@PathVariable long todoId){
        todoService.withdraw(todoId);
    }

    @PatchMapping("/{todoId}")
    TodoResponse updateTodo(@PathVariable long todoId, @RequestBody TodoDto todoDto){
        TodoResponse response =  todoService.update(todoId, todoDto);
        return response;
    }

    @DeleteMapping("{todoId}")
    void deleteTodo(@PathVariable long todoId){
        todoService.delete(todoId);
    }

    //TODO RECORD: DTO
    public record TodoDto(String content) {

    }

    //TODO RECORD: RESPONSE
    public record TodoResponse(long todoId,
                               Todo.TodoStatus todoStatus,
                               String content,
                               LocalDateTime createdAt,
                               LocalDateTime modifiedAt) {
        public static TodoResponse from(Todo todo) {
            return new TodoResponse(todo.getId(),
                    todo.getStatus(),
                    todo.getContent(),
                    todo.getCreatedAt(),
                    todo.getModifiedAt());
        }
    }
}
