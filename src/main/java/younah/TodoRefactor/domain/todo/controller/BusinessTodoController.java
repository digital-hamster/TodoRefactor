package younah.TodoRefactor.domain.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import younah.TodoRefactor.domain.todo.service.BusinessTodoService;


@RestController
@RequestMapping("/todos/{todoId}")
@RequiredArgsConstructor
public class BusinessTodoController {
    private final BusinessTodoService service;

    @PatchMapping("/complete")
    void complete(@PathVariable long todoId){
        service.complete(todoId);
    }

    @PatchMapping("/withdraw")
    void withdraw(@PathVariable long todoId){
        service.withdraw(todoId);
    }
}
