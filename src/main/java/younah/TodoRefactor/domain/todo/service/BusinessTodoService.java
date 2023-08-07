package younah.TodoRefactor.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.repository.TodoRepository;
import younah.TodoRefactor.global.exception.BusinessLogicException;
import younah.TodoRefactor.global.exception.ExceptionCode;

@Service
@RequiredArgsConstructor
public class BusinessTodoService {
    private final TodoRepository todoRepo;

    @Transactional
    public void complete(long todoId){
        var targetTodo = todoRepo.findById(todoId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_EXSIST));

        targetTodo.complete();
        todoRepo.save(targetTodo);
    }

    @Transactional
    public void withdraw(long todoId){
        var targetTodo = todoRepo.findById(todoId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_EXSIST));

        targetTodo.withdraw();
        todoRepo.save(targetTodo);
    }
}
