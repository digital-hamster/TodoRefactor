package younah.TodoRefactor.domain.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import younah.TodoRefactor.domain.common.BaseTimeEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor //생성자를 만들기 위한 기본 생성자 추가 , feedback: 이렇게하면 어느곳에서든 기본생성자로 Todo 엔티티를 생성할 수 있게됨 접근제한자를 지정하시오
@SQLDelete(sql = "UPDATE todo SET deleted = true, deleted_at = NOW() WHERE id = ?") // 쓰지마시오
@Where(clause = "deleted = false") // 쓰지마시오
public class Todo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String content;

    @Enumerated(value = EnumType.STRING)
    @Column
    private TodoStatus status = TodoStatus.TODO_BEFORE;

    //TODO SOFT DELETE
    private final boolean deleted = Boolean.FALSE; // 필요없습니다

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
        this.status = TodoStatus.TODO_BEFORE; //내가 생성자에서 초기화 하는걸 선호함
    }


    public static Todo from(String content) {
        Todo todo = new Todo(content);
        todo.content = content;
        return todo;
    }

    //TODO) 내부로직
    public void complete(){
        //이게 불가능할 상황이 뭐가 있지? ... 없음. 예외 꾸역꾸역 넣어보고 싶었는데 ... Feedback: 없으면 굳이 안넣어도됨 ㅎㅎ
        this.status = TodoStatus.TODO_DONE;
    }

    public void setBack(){
        this.status = TodoStatus.TODO_BEFORE;
    } // withdraw가 더 낫지않을까?
    public void updateContent(String content){
        this.content = content;
    }
}