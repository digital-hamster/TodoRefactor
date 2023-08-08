package younah.TodoRefactor.domain.todo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import younah.TodoRefactor.domain.todo.entity.Todo;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TodoRepositoryCustom {
    Page<Todo> findSearchTodos(SearchCondition searchCondition, Pageable pageable);

    record SearchCondition( //여기에 검색하려는 레코드 정의 (페이지 정보를 제외한 검색하려는 정보)
            Optional<Todo.TodoStatus> status,
            Optional<LocalDateTime> from,
            Optional<LocalDateTime> to
    ){}
}
