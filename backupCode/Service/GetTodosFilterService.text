package younah.TodoRefactor.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.repository.TodoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTodosFilterService {
    private final TodoRepository todoRepo;

    @Transactional(readOnly = true)
    public List<TodoDto> getCompleteTodos() {

        List<Todo> todos = todoRepo
                .findActiveTodos();

        return findCompleteTodos(todos);
    }

    @Transactional(readOnly = true)
    public List<TodoDto> getBeforeTodos() {

        List<Todo> todos = todoRepo
                .findActiveTodos();

        return findBeforeTodos(todos);
    }

    //@Query 사용하는 방법이랑, method로 쓰는 방법이랑 둘 중에 뭘 더 선호할지 몰라서 repo에 쿼리문도 작성
     //ㄴ> 근데 이미 꺼내온 객체(todos)에서 접근해서 확인할 수 있으니까, repository로 db에 또 접근해서 가져올 필요는 없지 않아?
    public List<TodoDto> findCompleteTodos(List<Todo> todos){
        return todos.stream()
                .filter(todo -> todo.getStatus() == Todo.TodoStatus.TODO_COMPLETE)
                .map(TodoDto::fromEntity)
                .toList();
    }

    public List<TodoDto> findBeforeTodos(List<Todo> todos){
        return todos.stream()
                .filter(todo -> todo.getStatus() == Todo.TodoStatus.TODO_BEFORE)
                .map(TodoDto::fromEntity)
                .toList();
    }
}
