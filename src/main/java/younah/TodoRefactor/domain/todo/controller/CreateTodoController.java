package younah.TodoRefactor.domain.todo.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import younah.TodoRefactor.domain.todo.service.CreateTodoService;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@Validated
public class CreateTodoController {
    private final CreateTodoService service;

    @PostMapping
    Response createTodo(@RequestBody Request request){
        var todoDto = service.create(request.toRequirement());

        return new Response(todoDto.id());
    }

    record Request(
        @NotNull(message = "content는 필수입니다.")
        String content
    ) {
        public CreateTodoService.Requirement toRequirement() {
            return new CreateTodoService.Requirement(content);
        }
    }

    record Response(
        Long id
    ) {
    }
}
