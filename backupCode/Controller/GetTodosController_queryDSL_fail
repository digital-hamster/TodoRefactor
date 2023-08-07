package younah.TodoRefactor.domain.todo.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import younah.TodoRefactor.domain.common.ApiResponse;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.service.GetTodosService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class GetTodosController {
        private final GetTodosService service;

    @GetMapping
    ApiResponse<Page<Response>> getTodos(@RequestParam("todoStatus") String status,
                                         @RequestParam("startAt")
                                         @NotBlank(message = "검색 시작 날짜를 넣어주세요")
                                         LocalDateTime startAt,
                                         @RequestParam("endAt") LocalDateTime endAt,
                                         @RequestParam(value = "page", defaultValue = "1") int page) {

        Pageable pageable = PageRequest.of(page - 1, 3);
            //defaultValue를 1로 설정해도, Pageabled의 최종 반환은 0부터 시작하기 때문에 1부터 시작할 수 있도록
        Todo.TodoStatus todoStatus = Todo.statusFromString(status);

        Page<TodoDto> todos = service.getTodos(pageable,
                todoStatus,
                startAt,
                endAt);

        Page<Response> responses = Response.to(todos);

        return ApiResponse.success(responses);
    } //페이지의 정보를 받아서 response에서 만지고 싶으면 service의 반환값도 Page여야 함 ...

    record Response(
            Long id,
            String content,
            Todo.TodoStatus status
    ) {
        static Page<Response> to(Page<TodoDto> todos) {
            List<Response> listTodos = todos.getContent().stream()
                    .map(todoDto -> new Response(
                            todoDto.id(),
                            todoDto.content(),
                            todoDto.status()
                    ))
                    .collect(Collectors.toList());

            return new PageImpl<>(listTodos, todos.getPageable(), todos.getTotalElements());
            //ㄴ> 페이지네이션의 정보를 담기 위해서 pageImpl<>을 쓴다는. 어딘가의 정보.
        }
    }
}