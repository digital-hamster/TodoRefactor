package younah.TodoRefactor.domain.todo.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.response.TodoResponse;
import younah.TodoRefactor.domain.todo.service.TodoService;

//import javax.validation.constraints.*; //TODO 아니 왜 build.gradle 추가해도 안돌아?

//notnull: import com.sun.istack.NotNull; << 자바에서 잘 안씀

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
@Validated
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public TodoResponse createTodo(@NotNull @RequestBody TodoDto todoDto){
        String content = todoDto.getContent();
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
}
