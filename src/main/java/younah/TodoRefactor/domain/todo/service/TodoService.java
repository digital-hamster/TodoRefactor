package younah.TodoRefactor.domain.todo.service;

//import younah.TodoRefactor.domain.todo.response.TodoResponse;
//import younah.TodoRefactor.domain.todo.dto.TodoDto;

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


    @Transactional(readOnly = true)
    public TodoController.TodoResponse getTodo(long todoId) {
        Todo targetTodo = todoRepo.findById(todoId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_EXSIST));

        return TodoController.TodoResponse.from(targetTodo);
    }


    @Transactional(readOnly = true)
    public List<TodoController.TodoResponse> getTodos() {
        List<Todo> todos = todoRepo.findAll(Sort.by("id").descending());

            List<TodoController.TodoResponse> todoResponses = todos.stream()
                    .map(TodoController.TodoResponse::from)
                    .collect(Collectors.toList());

    return todoResponses;
    }


    @Transactional
    public void  todoComplete(long todoId){
        Todo targetTodo = todoRepo.findById(todoId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_EXSIST));
            targetTodo.complete();
            todoRepo.save(targetTodo);
    }

    @Transactional
    public void todoSetBack(long todoId){
        Todo targetTodo = todoRepo.findById(todoId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_EXSIST));
            targetTodo.setBack();
            todoRepo.save(targetTodo);
    }

    @Transactional
    public TodoController.TodoResponse updateContent(long todoId, TodoController.TodoDto todoDto){
        Todo targetTodo = todoRepo.findById(todoId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_EXSIST));
            targetTodo.updateContent(todoDto.content());
            todoRepo.save(targetTodo);

    return TodoController.TodoResponse.from(targetTodo);

        }

        @Transactional
        public void deleteTodo(long todoId){
            todoRepo.deleteById(todoId);
        }


    }
