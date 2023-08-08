package younah.TodoRefactor.domain.todo.repository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Wildcard;
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

@Repository
@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Autowired
    public TodoRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<Todo> findSearchTodos(SearchCondition searchCondition,
                                  Pageable pageable) {
        QTodo todo = QTodo.todo;

        var where = new Predicate[]{
            //status의 where
            searchCondition.status()
                    .map(todo.status::eq) //같다면!
                    .orElse(null), //todo.status가 Null인 경우, 사용자가 값을 넣지 않은 경우 null

            //startedAt
            searchCondition.from()
                    .map(todo.createdAt::goe) //시작 날짜보다 이상
                    .orElse(null),

            //endedAt
            searchCondition.to()
                    .map(todo.createdAt::loe) //시작 날짜보다 이하
                    .orElse(null),
        };

        //total에 대한 정보가 있어야 페이지네이션 객체 생성 가능
        var countQuery = queryFactory
                .select(Wildcard.count) // ==select count(*) from todo
                .from(todo)
                .where(where) //where 조건에 대한 count 이행
                .fetchOne();
        //ㄴ> 쿼리 계산 카운트 먼저 호출하기
            //ㄴ> 0개면 진짜 query 안날리고 그냥 empty 보내면됨 (2번 보낼 쿼리 2번으로 처리)

        var query = queryFactory
                .selectFrom(todo) //Project.const 어쩌구 쓰면 바로 dto 반환
                .where(where)
                .offset(pageable.getOffset()) //클라이언트로부터 pageable에 있는 페이지의 값을 받아올 수 있음
                .limit(pageable.getPageSize()) //쿼리로 한도 정하기 가능
                .fetch(); //리스트 반환, 쿼리에 붙여서 반환 가능


        //페이지에 대한 검색 결과가 없을 때 빈 거 반환해줘야함 페이지 어쩌구였음
        if (countQuery==0 || countQuery==null){
            return Page.empty(); //얘가 페이지에서 빈 객체를 반환
        }

        return new PageImpl<>(
                query,
                pageable,
                countQuery
        );
    }
}