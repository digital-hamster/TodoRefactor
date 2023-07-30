package younah.TodoRefactor.domain.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import younah.TodoRefactor.domain.common.ApiResponse;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.service.GetTodosToPageService;

import java.util.List;
import java.util.stream.Collectors;

//굳이 컨트롤러 만든 이유: 뭔가 .... 애매함. 이 애매함으로 원래의 getTodos를 버리고 싶지 않은 ... 욕심...

@RestController
@RequestMapping("/todos/page")
@RequiredArgsConstructor
public class GetTodosToPageController {
        private final GetTodosToPageService service;

    @GetMapping
    ApiResponse<Page<Response>> getTodos(@RequestParam(value = "page", defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page, 3);

        Page<TodoDto> todos = service.getTodos(pageable);
        Page<Response> responses = Response.to(todos);

        return ApiResponse.success(responses);
    }

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