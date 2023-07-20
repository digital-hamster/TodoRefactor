//package younah.TodoRefactor.domain.todo.response;
//
//import lombok.Getter;
//import younah.TodoRefactor.domain.todo.entity.Todo;
//
//import java.time.LocalDateTime;
//
////TODO todoResponse가 서비스 객체를 거치지 않는 값 객체가 될 거임
//
//@Getter
//public class TodoResponse {
//    long todoId;
//    Todo.TodoStatus todoStatus;
//    String content;
//    LocalDateTime createdAt;
//    LocalDateTime modifiedAt;
//
//    public static TodoResponse from(Todo todo){
//        TodoResponse response = new TodoResponse();
//
//        response.todoId = todo.getId();
//        response.todoStatus = todo.getStatus();
//        response.content = todo.getContent();
//        response.createdAt = todo.getCreatedAt();
//        response.modifiedAt = todo.getModifiedAt();
//
//        return response;
//    }
//}
