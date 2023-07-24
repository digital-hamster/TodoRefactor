package younah.TodoRefactor.domain.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import younah.TodoRefactor.domain.todo.service.CompleteTodoService;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class CompleteTodoController {
    private final CompleteTodoService service;

    @PatchMapping("complete/{todoId}")
    void todoDone(@PathVariable long todoId){
        service.complete(todoId);
    }

}
