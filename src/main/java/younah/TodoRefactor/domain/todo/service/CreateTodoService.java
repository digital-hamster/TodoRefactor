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
        var newTodo = requirement.toEntity();
        var savedTodo = todoRepo.save(newTodo);

        return TodoDto.fromEntity(savedTodo);
    }

    public record Requirement(
            String content
    ){
        public Todo toEntity(){
            return new Todo(content);
        }
    }
}
