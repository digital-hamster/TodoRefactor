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
public class UpdateTodoService {
    private final TodoRepository todoRepo;

    @Transactional
    public void update(long todoId, Requirement requirement){

        Todo targetTodo = todoRepo.findById(todoId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_EXSIST));

        targetTodo.update(requirement.content());
        //수정을 위해 받아온 requirement를 entity로 변환해야 하는가? ?? ㄴㄴ 바로 content 뽑아쓰기
        //어차피 service 객체로 오기 위한 requirement로 변환했음 !!

        todoRepo.save(targetTodo);
    }

    public record Requirement(
            String content
    ){ }
}
