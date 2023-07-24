package younah.TodoRefactor.domain.todo.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import younah.TodoRefactor.domain.todo.controller.TodoController;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.repository.TodoRepository;

import org.springframework.transaction.annotation.Transactional;
import younah.TodoRefactor.global.exception.BusinessLogicException;
import younah.TodoRefactor.global.exception.ExceptionCode;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepo;

    @Transactional
    public TodoController.TodoResponse createTodo(String content) {

        Todo todo = Todo.from(content);
        todoRepo.save(todo);

        return TodoController.TodoResponse.from(todo);
    }


    @Transactional(readOnly = true) //TODO softDelete가 안보이도록
    public TodoController.TodoResponse getTodo(long todoId) {
        Todo targetTodo = todoRepo.findById(todoId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_EXSIST));

        return TodoController.TodoResponse.from(targetTodo);
    }


    //ㄴ> deletedAt < 이 null인지 아닌지
    //로직에서 쓸 때 isDeleted(){
		//return deletat != null //이런 식으로 ~하기

    @Transactional(readOnly = true)
    public List<TodoController.TodoResponse> getTodos() {
        List<Todo> todos = todoRepo
                .findAll(Sort.by("id")
                .descending());

        List<TodoController.TodoResponse> todoResponses = todos.stream()
                .map(TodoController.TodoResponse::from)
                .collect(Collectors.toList());

        return todoResponses;
    }


    @Transactional
    public void  complete(long todoId){
        Todo targetTodo = todoRepo.findById(todoId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_EXSIST));

        targetTodo.complete();
        todoRepo.save(targetTodo);
    }

    @Transactional
    public void withdraw(long todoId){
        Todo targetTodo = todoRepo.findById(todoId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_EXSIST));

        targetTodo.withdraw();
        todoRepo.save(targetTodo);
    }

    @Transactional
    public TodoController.TodoResponse update(long todoId, TodoController.TodoDto todoDto){
        Todo targetTodo = todoRepo.findById(todoId)
                  .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_EXSIST));

            targetTodo.update(todoDto.content());
            todoRepo.save(targetTodo);

            return TodoController.TodoResponse.from(targetTodo); //// 컨트롤러가 여기 왜 있어요 서비스는 컨트롤러를 몰라야됨
        //ㄴ> 멘토님은 이거 해결책으로 todoDto를 스심

        }

    @Transactional
    public void delete(long todoId){
        Todo targetTodo = todoRepo.findById(todoId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_EXSIST));

        targetTodo.remove();
        todoRepo.save(targetTodo);
    }

}
