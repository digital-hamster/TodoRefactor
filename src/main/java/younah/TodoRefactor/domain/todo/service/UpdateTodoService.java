package younah.TodoRefactor.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.repository.TodoRepository;
import younah.TodoRefactor.global.exception.BusinessLogicException;
import younah.TodoRefactor.global.exception.ExceptionCode;

@Service
@RequiredArgsConstructor
public class UpdateTodoService {
    private final TodoRepository todoRepo;

    @Transactional
    public TodoDto update(long todoId, Requirement requirement){

        Todo newTodo = requirement.toEntity();

        Todo targetTodo = todoRepo.findById(todoId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_EXSIST));

        targetTodo.update(newTodo.getContent());
        //수정을 위해 받아온 requirement를 entity로 변환해야 하는가? ??
        // ㄴ> 음 ... 값만 받는데 그걸 requestBody로 받기 위해서 이런거임 ... 얘를 entity로 바꿀 필요는 없다 생각함
            //ㄴ> 이래놓고 다시 수정 컨트롤러 서비스 침범 멈춰
        todoRepo.save(targetTodo);

        return TodoDto.fromEntity(targetTodo);
    }

    public record Requirement(
            String content
    ){
        public Todo toEntity(){
        return new Todo(content);
    }
    }
}
