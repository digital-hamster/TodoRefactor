package younah.TodoRefactor.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.repository.TodoRepository;

@Service
@RequiredArgsConstructor
public class CreateTodoService {
    private final TodoRepository todoRepo;

    @Transactional
    public TodoDto create(Requirement requirement){
        Todo newTodo = requirement.toEntity(); //requirement -> entity 변환
        Todo savedTodo = todoRepo.save(newTodo); //비즈니스 로직

        return TodoDto.fromEntity(savedTodo); //Entity -> Dto 변환 및 컨트롤러 반환
    }


    public record Requirement( //service로 받아온 객체를 entity로 변환
            String content
    ){
        public Todo toEntity(){
            return new Todo(content);
        }
    }

}
