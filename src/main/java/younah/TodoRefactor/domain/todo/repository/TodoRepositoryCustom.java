package younah.TodoRefactor.domain.todo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import younah.TodoRefactor.domain.todo.entity.Todo;

import java.time.LocalDateTime;

public interface TodoRepositoryCustom {
    Page<Todo> findByDates(LocalDateTime startAt, LocalDateTime endAt, Pageable pageable);
}
