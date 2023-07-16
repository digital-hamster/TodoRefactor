package younah.TodoRefactor.domain.todo.controller;

import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.response.TodoResponse;
import younah.TodoRefactor.domain.todo.service.TodoService;

//import javax.validation.constraints.*; //TODO 아니 왜 build.gradle 추가해도 안돌아?
// 이유는 spring boot 3.0 부터 javax 패키지를 지원안하기 때문일듯?
//notnull: import com.sun.istack.NotNull; << 자바에서 잘 안씀

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity saveTodo(@RequestBody TodoDto todoDto){ // save라는 어감이 뭔가 repo.save 즉 영속화하는것 같아서 차라리 createTodo가 더 낫지 않을까?
        String content = todoDto.getContent();
        ResponseEntity response = todoService.createTodo(content);

        return response;
    }

    @GetMapping
    public List<TodoResponse> getTodos(){
        return todoService.getTodos();
    }

    @GetMapping("/{todo_id}")
    public ResponseEntity getTodo(@PathVariable("todo_id") long todoId){
        return todoService.getTodo(todoId);
    }



    @PatchMapping("/completion/{todo_id}") //부분적 변경(patch)
    public void todoDone(@PathVariable("todo_id") long todoId){ // 메서드 이름 왜이래 todoDone -> done or complete 로 변경하기
        todoService.todoDone(todoId);
    }


    @PatchMapping("/withdraw/{todo_id}")//부분적 변경 -> todo/{todoId}/withdraw 로 변경할 것
    public void todoWithDraw(@PathVariable("todo_id") long todoId){
        todoService.todoWithDraw(todoId);
    }

    //내용을 수정하는 api
    @PatchMapping("/{todo_id}")//부분적 변경
    public ResponseEntity updateTodo(@PathVariable("todo_id") long todoId, @RequestBody TodoDto todoDto){
        ResponseEntity response =  todoService.updateContent(todoId, todoDto);
        return response;
    }

    @DeleteMapping("{todo_id}") // 그냥 todoId 로 할것
    public void deleteTodo(@PathVariable("todo_id") long todoId){
        todoService.deleteTodo(todoId);
    }
}
