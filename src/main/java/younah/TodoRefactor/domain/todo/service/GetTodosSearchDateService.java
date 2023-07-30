package younah.TodoRefactor.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.repository.TodoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTodosSearchDateService {
    private final TodoRepository todoRepo;

    @Transactional(readOnly = true)
    public List<TodoDto> dateSearch(LocalDateTime startAt,
                                    LocalDateTime endAt){
        List<Todo> todos = todoRepo
                .findByDates(startAt, endAt);

        return to(todos);
    }

    public List<TodoDto> to(List<Todo> todos){
        return todos.stream()
                .map(TodoDto::fromEntity)
                .toList();
    }
}
