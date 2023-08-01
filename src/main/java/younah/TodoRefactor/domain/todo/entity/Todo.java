package younah.TodoRefactor.domain.todo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import younah.TodoRefactor.domain.common.BaseTimeEntity;
import younah.TodoRefactor.global.exception.BusinessLogicException;
import younah.TodoRefactor.global.exception.ExceptionCode;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
        TODO_COMPLETE("활동 완료");

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
        this.status = TodoStatus.TODO_COMPLETE;
    }

    public void withdraw(){
        this.status = TodoStatus.TODO_BEFORE;
    }
    public void update(String content){
        this.content = content;
    }

    //TODO String 타입 enum으로 바꾸기
    public static TodoStatus statusFromString(String status) {//다른 상태값이 들어오면 controller에서 변환과 동시에 예외
        for (TodoStatus todoStatus : TodoStatus.values()) {
            if (todoStatus.name().equals(status)) { //.getStatus로는 검색을 못 해서 .name() 이용
                return todoStatus;
            }
        }
        throw new BusinessLogicException(ExceptionCode.STATUS_NOT_EXSIST);
    }
}