package younah.TodoRefactor.domain.todo.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.repository.TodoRepository;
import younah.TodoRefactor.domain.todo.response.TodoResponse;
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
    public TodoResponse createTodo(String content) {

        Todo todo = Todo.from(content);
        todoRepo.save(todo);

        return TodoResponse.from(todo);
    }


    @Transactional(readOnly = true)
    public TodoResponse getTodo(long todoId) {
        Todo targetTodo = todoRepo.findById(todoId).orElseThrow(() -> new IllegalStateException("존재하지 않는 todo입니다."));

        return TodoResponse.from(targetTodo);
    }


    @Transactional(readOnly = true)
    public List<TodoResponse> getTodos() {
        List<Todo> todos = todoRepo.findAll(Sort.by("id").descending());


        List<TodoResponse> todoResponses = todos.stream()
                .map(TodoResponse::from)
                .collect(Collectors.toList());

    return todoResponses;
    }


    @Transactional
    public void  todoComplete(long todoId){
        Todo targetTodo = todoRepo.findById(todoId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_EXSIST));
        targetTodo.complete();
    }

    @Transactional
    public void todoSetBack(long todoId){
        Todo targetTodo = todoRepo.findById(todoId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_EXSIST));
        targetTodo.setBack();
    }

    @Transactional
        public TodoResponse updateContent(long todoId, TodoDto todoDto){
            Todo targetTodo = todoRepo.findById(todoId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_EXSIST));

                targetTodo.updateContent(todoDto.getContent());
                todoRepo.save(targetTodo);

        return TodoResponse.from(targetTodo);

        }

        @Transactional
        public void deleteTodo(long todoId){
            todoRepo.deleteById(todoId);
        }


    }
