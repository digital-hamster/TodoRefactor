package younah.TodoRefactor.domain.todo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import younah.TodoRefactor.domain.common.BaseTime;

import java.time.LocalDateTime;

@Entity
@Getter
public class Todo extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String content;

    @Column
    private boolean isDone; //미완료:false / 완료:true

    @Enumerated(value = EnumType.STRING)
    @Column
    private TodoStatus status = TodoStatus.TODO_BEFORE;


    @Getter
    @RequiredArgsConstructor
    public enum TodoStatus {
        TODO_BEFORE("활동 전"),
        TODO_DONE("활동 완료");

        private String status; // final 붙이기

        TodoStatus(String status) { // 생성자 삭제 해도 됨
            this.status = status;
        }
    }

    //TODO) 정적 팩토리 메소드(컨벤션)
    public static Todo from(String content) { //@Setter 사용금지!
        Todo todo = new Todo(); // 기본 생성자말고 필요한 생성자를 직접 만들자
        todo.content = content;
        return todo;
    }


    //TODO) Validation // 애초에 이 검증 로직은 컨트롤러에서 수행하는거라 엔티티에는 필요없다 메서드를 지워도 무방
    public void todoValidation(@NotNull Todo todo){ // 메서드는 동사형으로 쓰자 ex) validate 
        if(todo.getId()==0){ 
            throw new IllegalArgumentException("존재하지 않는 todo입니다.");
        }

        if(todo.getContent().length()==0){
            throw new IllegalArgumentException("todo의 내용을 입력해 주세요.");
        }
    }

    //TODO) 내부로직
    public void done(){
        this.isDone = true; // TodoStatus가 잇는데 isDone 은 왜 있어야되는걸까? 둘중 하나만 쓰자
        this.status = TodoStatus.TODO_DONE;
        updateModifiedAt(); // 지우자
    }

    public void withDraw(){
        this.isDone = false;
        this.status = TodoStatus.TODO_BEFORE;
        updateModifiedAt();
    }
    public void updateContent(String content){
        this.content = content;
        updateModifiedAt(); // 지우자
    }
}