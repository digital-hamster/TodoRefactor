package younah.TodoRefactor.domain.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import younah.TodoRefactor.domain.common.ApiResponse;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.service.GetTodosFilterService;

import java.util.List;
import java.util.stream.Collectors;

//response가 겹치고 필터링 빼면 하는 일이 똑같아서 하나의 클래스로 처리

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
class GetTodosFilterController {

    private final GetTodosFilterService service;

    @GetMapping("/complete")
    ApiResponse<List<Response>> getCompleteTodos() {
        List<TodoDto> todos = service.getCompleteTodos();

        List<Response> responses = Response.to(todos);
        return ApiResponse.success(responses);
    }

    @GetMapping("/before")
    ApiResponse<List<Response>> getBeforeTodos() {
        List<TodoDto> todos = service.getBeforeTodos();

        List<Response> responses = Response.to(todos);
        return ApiResponse.success(responses);
    }

    record Response(
            Long id,
            String content,
            Todo.TodoStatus status
    ){
        static List<GetTodosFilterController.Response> to(List<TodoDto> todos) {
            return todos.stream()
                    .map(todoDto -> new GetTodosFilterController.Response(
                            todoDto.id(),
                            todoDto.content(),
                            todoDto.status()
                    ))
                    .collect(Collectors.toList());
        }
    }
}
