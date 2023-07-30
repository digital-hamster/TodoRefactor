package younah.TodoRefactor.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.repository.TodoRepository;

@Service
@RequiredArgsConstructor
public class GetTodosToPageService {
    private final TodoRepository todoRepo;

    @Transactional(readOnly = true)
    public Page<TodoDto> getTodos(Pageable pageable) {

        Page<Todo> todos = todoRepo
                .findActiveTodos(pageable);

        return todos
                .map(TodoDto::fromEntity);
    }
}