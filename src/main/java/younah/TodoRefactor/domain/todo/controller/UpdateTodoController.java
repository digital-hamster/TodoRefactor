package younah.TodoRefactor.domain.todo.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import younah.TodoRefactor.domain.todo.service.UpdateTodoService;

//request가 존재한다면, 해당 request 안에 requirement로 변환해주는 메소드를 지니고 있어야 함
//동시에, 해당 service에 requirement가 선언되어 있어야 함 (record)

@RestController
@RequestMapping("/todos/{todoId}")
@RequiredArgsConstructor
@Validated
class UpdateTodoController {

    private final UpdateTodoService service;

    @PutMapping
    void updateTodo(@RequestBody Request request,
                        @PathVariable long todoId){
        //requirement 변환
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
