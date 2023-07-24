package younah.TodoRefactor.domain.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.service.GetTodosService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
class GetTodosController {
    private final GetTodosService service;

    @GetMapping
    List<Response> getTodos(){
        List<TodoDto> todos = service.getTodos();

        return todos.stream() // converting 하는 로직이 너무 길다 그냥 Private Method로 빼자
                .map(todoDto -> new Response(
                        todoDto.id(),
                        todoDto.content(),
                        todoDto.status()
                ))
                .collect(Collectors.toList());
    }

    record Response(
            Long id,
            String content,
            Todo.TodoStatus status
    ){}
}
