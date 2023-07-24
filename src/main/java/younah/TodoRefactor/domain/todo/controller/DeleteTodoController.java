package younah.TodoRefactor.domain.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.service.DeleteTodoService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class DeleteTodoController {
    private final DeleteTodoService service;

    @DeleteMapping("/{todoId}")
    Response delete(@PathVariable long todoId){
        TodoDto todoDto = service.remove(todoId);
        return new Response(todoDto.id(), todoDto.deletedAt());
    }

    record Response( // 응답이 꼭 필요한 것인지 생각해봅시다
            Long id,
            LocalDateTime deletedAt // 이게 꼭 필요한가??
    ){}
}
