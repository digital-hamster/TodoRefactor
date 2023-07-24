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
class WithdrawTodoController { // complete, withdraw 하나로 합치기
    private final WithdrawTodoService service;

    @PatchMapping("/withdraw/{todoId}") // 어차피 메서드 하나니까 endpoint는 RequestMapping에 적자
    void withdraw(@PathVariable long todoId){
        service.withdraw(todoId);
    }

}
