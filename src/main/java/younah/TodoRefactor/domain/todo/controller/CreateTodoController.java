package younah.TodoRefactor.domain.todo.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import younah.TodoRefactor.domain.common.ApiResponse;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.service.CreateTodoService;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@Validated
class CreateTodoController {
    private final CreateTodoService service;

    @PostMapping(produces = "application/json")
    Response CreateTodo(@RequestBody Request request){
        TodoDto todoDto = service.create(request.toRequirement());

        //Response response =  new Response(todoDto.id());
       // return ApiResponse.success(response);

        return new Response(todoDto.id());
    }

    record Request(
            @NotBlank(message = "content는 필수입니다.")
            String content
    ) {
        public CreateTodoService.Requirement toRequirement(){
            return new CreateTodoService.Requirement(content);
        }
    }
    record Response(
            Long id
    ){

    }
}
