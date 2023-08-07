package younah.TodoRefactor.domain.todo.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import younah.TodoRefactor.domain.common.ApiResponse;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.service.GetTodosService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class GetTodosController {
        private final GetTodosService service;

    @GetMapping
    ApiResponse<Page<Response>> getTodos(Request request,
                                         Pageable pageable) {

        //requirement 변환
        var requirement = new GetTodosService.Requirement(
                request.status(),
                request.from(),
                request.to(),
                pageable
        );

        Page<TodoDto> todos = service.getTodos(requirement);

        Page<Response> responses = Response.from(todos);

        return ApiResponse.success(responses);
    }

    record Request( //optional로 받아서 선택적 기입 가능하도록
            Optional<Todo.TodoStatus> status,
            Optional<LocalDateTime> from, //startedAt
            Optional<LocalDateTime> to, //endedAt
            @NotNull
            Pageable pageable
    ){}

    record Response(
            Long id,
            String content,
            Todo.TodoStatus status
    ) {
        static Page<Response> from(Page<TodoDto> todos) {
            var listTodos = todos.getContent().stream()
                    .map(todoDto -> new Response(
                            todoDto.id(),
                            todoDto.content(),
                            todoDto.status()
                    ))
                    .collect(Collectors.toList());

            return new PageImpl<>(listTodos, todos.getPageable(), todos.getTotalElements());
        }
    }
}