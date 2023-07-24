package younah.TodoRefactor.domain.todo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import younah.TodoRefactor.domain.common.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@SQLDelete(sql = "UPDATE todo SET deleted = true, deleted_at = NOW() WHERE id = ?")
//@Where(clause = "deleted = false")
public class Todo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Enumerated(value = EnumType.STRING)
    @Column
    private TodoStatus status;

    @Column(name= "deleted_at")
    private LocalDateTime deletedAt;


    @Getter
    @RequiredArgsConstructor
    public enum TodoStatus {
        TODO_BEFORE("활동 전"),
        TODO_DONE("활동 완료");

        private final String status;
    }

    //TODO 생성자
    public Todo(String content) {
        this.content = content;
        this.status = TodoStatus.TODO_BEFORE;
    }


    public static Todo from(String content) {
        Todo todo = new Todo(content);
        todo.content = content;
        return todo;
    }

    //TODO) SOFT DELETE
    public void remove(){
        this.deletedAt = LocalDateTime.now();
    }

    //TODO) 내부로직
    public void complete(){
        this.status = TodoStatus.TODO_DONE;
    }

    public void withdraw(){
        this.status = TodoStatus.TODO_BEFORE;
    }
    public void update(String content){
        this.content = content;
    }

    public static List<Todo> isDeleted(List<Todo> todos) {
        return todos.stream()
                .filter(todo -> todo.getDeletedAt() != null)
                .collect(Collectors.toList());
    }

    //ㄴ> deletedAt < 이 null인지 아닌지
    //로직에서 쓸 때 isDeleted(){
    //return deletat != null //이런 식으로 ~하기
}