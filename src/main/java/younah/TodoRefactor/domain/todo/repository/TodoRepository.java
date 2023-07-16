package younah.TodoRefactor.domain.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import younah.TodoRefactor.domain.todo.entity.Todo;


public interface TodoRepository extends JpaRepository<Todo, Long> {
}
