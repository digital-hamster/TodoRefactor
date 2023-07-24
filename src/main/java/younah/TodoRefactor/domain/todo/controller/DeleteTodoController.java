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

    record Response(
            Long id,
            LocalDateTime deletedAt
    ){}
}
