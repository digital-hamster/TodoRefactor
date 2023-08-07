package younah.TodoRefactor.domain.todo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import younah.TodoRefactor.domain.todo.entity.Todo;

import java.time.LocalDateTime;
import java.util.List;


public interface TodoRepository extends JpaRepository<Todo, Long>
        ,QuerydslPredicateExecutor<Todo>
        ,TodoRepositoryCustom{
    @Query("select t from Todo t where t.deletedAt is null ORDER BY t.id DESC")
    List<Todo> findActiveTodos();

    @Query("select t from Todo t where t.deletedAt is null ORDER BY t.id DESC")
    Page<Todo> findActiveTodos(Pageable pageable);

    //TODO 상태 쿼리
    //@Query("SELECT t FROM Todo t WHERE t.status = 'TODO_BEFORE'")
    //List<Todo> findTodosByTodoBefore();


    //(기존) 날짜 검색 쿼리
    //@Query("SELECT t FROM Todo t WHERE t.createdAt BETWEEN :startAt AND :endAt")
    //List<Todo> findByDates(@Param("startAt") LocalDateTime startAt, @Param("endAt") LocalDateTime endAt);

    Page<Todo> findByDates(SearchCondition searchCondition, Pageable pageable); //여기서 searchCondition 받아옴
}
