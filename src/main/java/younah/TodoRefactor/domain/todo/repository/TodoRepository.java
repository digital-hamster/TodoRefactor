package younah.TodoRefactor.domain.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import younah.TodoRefactor.domain.todo.entity.Todo;

import java.util.List;


public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("select t from Todo t where t.deletedAt is null ORDER BY t.id DESC")
    List<Todo> findActiveTodos();

//    @Query("SELECT t FROM Todo t WHERE t.status = 'TODO_BEFORE'")
//    List<Todo> findTodosByTodoBefore();

//    @Query("SELECT t FROM Todo t WHERE t.status = 'TODO_COMPLETE'")
//    List<Todo> findTodosByTodoComplete();
}
