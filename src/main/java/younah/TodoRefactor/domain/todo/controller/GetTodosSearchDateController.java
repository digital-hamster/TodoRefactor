package younah.TodoRefactor.domain.todo.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import younah.TodoRefactor.domain.common.ApiResponse;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.service.GetTodosSearchDateService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos/search")
@RequiredArgsConstructor
class GetTodosSearchDateController {
    private final GetTodosSearchDateService service;

    @GetMapping
    public ApiResponse<List<Response>> getDateTodos(@RequestParam("startAt")
                                                        @NotBlank(message = "검색 시작 날짜를 넣어주세요")
                                                        LocalDateTime startAt,
                                       @RequestParam("endAt") LocalDateTime endAt){
        List<TodoDto> todos = service.dateSearch(startAt, endAt);
        List<Response> responses = Response.to(todos);
        return ApiResponse.success(responses);
    }

    record Response(
            Long id,
            String content,
            Todo.TodoStatus status
    ) {
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
