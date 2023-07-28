package younah.TodoRefactor.domain.todo.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import younah.TodoRefactor.domain.todo.service.UpdateTodoService;

@RestController
@RequestMapping("/todos/{todoId}")
@RequiredArgsConstructor
@Validated
class UpdateTodoController {

    private final UpdateTodoService service;

    @PutMapping
    void updateTodo(@RequestBody Request request,
                        @PathVariable long todoId){
        service.update(todoId, request.toRequirement());
    }

    public record Request(
            @NotBlank(message = "content는 필수입니다")
            String content
    ){
        public UpdateTodoService.Requirement toRequirement(){
            return new UpdateTodoService.Requirement(content);
        }
    }
}
