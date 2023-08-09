package younah.TodoRefactor.domain.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.repository.TodoRepository;
import younah.TodoRefactor.domain.todo.repository.TodoRepositoryCustom;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetTodosService {
    private final TodoRepository todoRepo;

    @Transactional(readOnly = true)
    public Page<TodoDto> getTodos(Requirement requirement) {

        //서비스에 있는 걸 repo로 넘기기 위해 (pageable, 그 외)
        var searchCondition = new TodoRepositoryCustom.SearchCondition(
                requirement.status,
                requirement.from,
                requirement.to
        );

        var todos = todoRepo
                .findSearchTodos(searchCondition, requirement.pageable());

        //Entity -> Dto
        var dtos = todos.getContent()
                .stream()
                .map(TodoDto::fromEntity)
                .toList();

        return new PageImpl<>( //page 인터페이스 구현 객체 만들어서 반환
                dtos, //repo에서 나온 페이지의 정보를 넘겨야 함
                todos.getPageable(), //offset+size 정보
                todos.getTotalElements()
        );
    }
    public record Requirement(
            Optional<Todo.TodoStatus> status,
            Optional<LocalDateTime> from,
            Optional<LocalDateTime> to,
            Pageable pageable
    ){}
}