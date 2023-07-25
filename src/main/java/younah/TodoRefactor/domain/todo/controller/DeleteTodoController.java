package younah.TodoRefactor.domain.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import younah.TodoRefactor.domain.todo.service.DeleteTodoService;

@RestController
@RequestMapping("/todos/{todoId}")
@RequiredArgsConstructor
public class DeleteTodoController {
    private final DeleteTodoService service;

    @DeleteMapping
    void delete(@PathVariable long todoId){
        service.remove(todoId);
    }
}
