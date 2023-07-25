package younah.TodoRefactor.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.repository.TodoRepository;

import java.util.List;
import java.util.stream.Collectors;

//받아올 값이 따로 없어서 requirement 는 안했음

@Service
@RequiredArgsConstructor
public class GetTodosService {

    private final TodoRepository todoRepo;

    @Transactional(readOnly = true)
    public List<TodoDto> getTodos() {

        List<Todo> todos = todoRepo
                .findActiveTodos();

        return todos.stream()
                .map(TodoDto::fromEntity)
                .toList();
    }

}
