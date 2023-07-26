package younah.TodoRefactor.domain.todo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import younah.TodoRefactor.domain.todo.dto.TodoDto;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class GetTodosServiceTest {

    private GetTodosService service;
    private TodoRepository todoRepo;

    @BeforeEach
    void setUp(){
        todoRepo = Mockito.mock(TodoRepository.class);
        service = new GetTodosService(todoRepo);
    }

    //말은 로직 서비스지만 ... 불러오는 값들을 확인하는 곳은 controller 아닌가?
    //그러면 dto를 최종적으로 확인해야 하는 거 아닌가??

    @Test
    void getTodos() { //목표: 값들을 불러올 때, deletedAt이 null이어야 함 (todo 생성자 추가)
        //given
        List<Todo> todos = new ArrayList<>(); //list 자체는 인터페이스
        todos.add(new Todo("Todo 1", null));
        todos.add(new Todo("Todo 2", null));
        //ㄴ> 실제 내가 정의한 repository 메소드는 못 쓰기 때문에 deletedAt에 데이터 채워봤자 소용 없음

        BDDMockito.given(todoRepo.findActiveTodos())
                .willReturn(todos);
                //ㄴ> 레포지토리 돌린 결과가 전부 null이 나와야 해서 willReturn

        //when
        List<TodoDto> todosResult = service.getTodos();
        //ㄴ> 컨트롤러에서 반환된 todo에 deleteAt이 null이어야 함

        //then
        for (TodoDto todoDto : todosResult) {
            assertThat(todoDto.deletedAt()).isNull();
        }
    }
}