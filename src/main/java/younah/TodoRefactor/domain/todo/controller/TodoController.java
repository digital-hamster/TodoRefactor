package younah.TodoRefactor.domain.todo.controller;

//import younah.TodoRefactor.domain.todo.response.TodoResponse;

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
@RequestMapping("/todo")
@RequiredArgsConstructor
@Validated
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public TodoResponse createTodo(@NotNull @RequestBody TodoDto todoDto){
        String content = todoDto.content();
        TodoResponse response = todoService.createTodo(content);

        return response;
    }

    @GetMapping
    public List<TodoResponse> getTodos(){
        return todoService.getTodos();
    }

    @GetMapping("/{todoId}")
    public TodoResponse getTodo(@PathVariable("todoId") long todoId){
        return todoService.getTodo(todoId);
    }

    @PatchMapping("/complete/{todoId}")
    public void todoDone(@PathVariable("todoId") long todoId){
        todoService.todoComplete(todoId);
    }

    @PatchMapping("/setback/{todoId}")
    public void todoWithDraw(@PathVariable("todoId") long todoId){
        todoService.todoSetBack(todoId);
    }

    @PatchMapping("/{todoId}")
    public TodoResponse updateTodo(@PathVariable("todoId") long todoId, @RequestBody TodoDto todoDto){
        TodoResponse response =  todoService.updateContent(todoId, todoDto);
        return response;
    }

    @DeleteMapping("{todoId}")
    public void deleteTodo(@PathVariable("todoId") long todoId){
        todoService.deleteTodo(todoId);
    }

    //TODO RECORD: DTO
    public record TodoDto(String content) {
        //동일한 패키지가 아니라서 service에서 접근할 수 없음 ...
        //얘를 public을 달지 말지 고민을 많이 했는데, 애초에 dto의 값 자체는 외부에서 오는 거니까 어차피 열린교회문. 아닌가 그래서 달앗삼
    }

    //TODO RECORD: RESPONSE
    public record TodoResponse(long todoId, Todo.TodoStatus todoStatus, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        public static TodoResponse from(Todo todo) {
            return new TodoResponse(todo.getId(), todo.getStatus(), todo.getContent(), todo.getCreatedAt(), todo.getModifiedAt());
        }
    }
}
