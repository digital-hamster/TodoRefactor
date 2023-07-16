package younah.TodoRefactor.domain.todo.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.repository.TodoRepository;
import younah.TodoRefactor.domain.todo.response.TodoResponse;

//readOnly 사용 Transactional
import org.springframework.transaction.annotation.Transactional;
//import jakarta.transaction.Transactional; << 얘는 readOnly 안 해줌 치사함..

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepo;

    @Transactional
    public ResponseEntity createTodo(String content) {

        Todo todo = Todo.from(content);
        todoRepo.save(todo);

        TodoResponse response = response(todo);
        return ResponseEntity.ok(response);
    }


    @Transactional(readOnly = true)
    public ResponseEntity getTodo(long todoId) {
        Todo targetTodo = todoRepo.findById(todoId).orElseThrow(() -> new IllegalStateException("존재하지 않는 todo입니다."));

        TodoResponse todoResponse = response(targetTodo);
        return ResponseEntity.ok(todoResponse);
    }


    @Transactional(readOnly = true)
    public List<TodoResponse> getTodos() { //TODO 내부코드 끌고와도 ... 하 ... 븨오 어케써
        List<Todo> todos = todoRepo.findAll(Sort.by("id").descending());


        List<TodoResponse> todoResponses = todos.stream()
                .map(this::response)
                .collect(Collectors.toList());

    return todoResponses;
    }


    @Transactional
    public void  todoDone(long todoId){
        Todo targetTodo = todoRepo.findById(todoId).orElseThrow(() -> new IllegalStateException("존재하지 않는 todo입니다."));
        targetTodo.done();
    }

    @Transactional
    public void todoWithDraw(long todoId){
        Todo targetTodo = todoRepo.findById(todoId).orElseThrow(() -> new IllegalStateException("존재하지 않는 todo입니다."));
        targetTodo.withDraw();
    }

    @Transactional
        public ResponseEntity updateContent(long todoId, TodoDto todoDto){
            Todo targetTodo = todoRepo.findById(todoId).orElseThrow(()->new IllegalStateException("존재하지 않는 todo입니다."));

                targetTodo.updateContent(todoDto.getContent());
                todoRepo.save(targetTodo);

                TodoResponse todoResponse = response(targetTodo);

            return ResponseEntity.ok(todoResponse);
        }

        public void deleteTodo(long todoId){
            todoRepo.deleteById(todoId);
        }

        public TodoResponse response(Todo todo) {
            return TodoResponse.fromTodo(todo);
        }


    }
