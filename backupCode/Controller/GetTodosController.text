package younah.TodoRefactor.domain.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import younah.TodoRefactor.domain.common.ApiResponse;
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
    ApiResponse<List<Response>> getTodos(){
        List<TodoDto> todos = service.getTodos();

        List<Response> responses = Response.to(todos);
        return ApiResponse.success(responses);
    }

    record Response(
            Long id,
            String content,
            Todo.TodoStatus status
    ){
        static List<Response> to(List<TodoDto> todos) {
            return todos.stream()
                    .map(todoDto -> new Response(
                            todoDto.id(),
                            todoDto.content(),
                            todoDto.status()
                    ))
                    .collect(Collectors.toList());
        }
    }
}