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
        List<Todo> todos = todoRepo.findAll(Sort.by("id").descending());

        return todos.stream()
            .map(TodoDto::fromEntity)
            .toList();
    }


    //entity를 거치는 메소드는 직접적으로 db의 접근이 있고, 그 값을 변경하는 것들만 넣는거라고 .. 생각됨
    //단순히 데이터를 걸러주는 건 엔티티가 할 일이 아니지 않을까..?
    public List<Todo> isDeleted(List<Todo> todos) {
        return todos.stream()
                .filter(todo -> todo.getDeletedAt() == null)
                .collect(Collectors.toList());
    }
}
