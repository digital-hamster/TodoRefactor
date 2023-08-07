package younah.TodoRefactor.domain.todo.repository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import younah.TodoRefactor.domain.todo.entity.QTodo;
import younah.TodoRefactor.domain.todo.entity.Todo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Autowired
    public TodoRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<Todo> findByDates(LocalDateTime startAt,
                                  LocalDateTime endAt,
                                  Pageable pageable) {
        QTodo todo = QTodo.todo;
        Predicate predicate = todo //where절의 조건 객체 타입
                .createdAt
                .between(startAt, endAt);

        JPAQuery<Todo> query = queryFactory
                .selectFrom(todo)
                .where(predicate);

        //페이지네이션 만큼의 쿼리 데이터를 가져오기 위해
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());

        List<Todo> todos = query.fetch(); //복수 반용

        //total에 대한 정보가 있어야 페이지네이션 객체 생성 가능
        JPAQuery<Long> countQuery = queryFactory
                .select(todo.id.count())
                .from(todo)
                .where(predicate);

        long total = countQuery.fetchOne(); //단일 반환

        return new PageImpl<>(todos, pageable, total);
    }
}