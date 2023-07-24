package younah.TodoRefactor.domain.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import younah.TodoRefactor.domain.todo.service.CompleteTodoService;

@RestController
@RequestMapping("/todos/{todoId}/complete") // todoId가 먼저입니다.
@RequiredArgsConstructor
public class CompleteTodoController {
    private final CompleteTodoService service;

    @PatchMapping
    void todoDone(@PathVariable long todoId){ // 메서드 바꿔!!
        service.complete(todoId);
    }

}
