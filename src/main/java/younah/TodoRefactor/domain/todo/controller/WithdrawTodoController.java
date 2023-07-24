package younah.TodoRefactor.domain.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import younah.TodoRefactor.domain.todo.service.WithdrawTodoService;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
class WithdrawTodoController {
    private final WithdrawTodoService service;

    @PatchMapping("/withdraw/{todoId}")
    public void withdraw(@PathVariable long todoId){
        service.withdraw(todoId);
    }

}
