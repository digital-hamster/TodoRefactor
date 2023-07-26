package younah.TodoRefactor.domain.todo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.repository.TodoRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteTodoServiceTest {

    private DeleteTodoService service;
    private TodoRepository todoRepo;

    @BeforeEach
    void setUp(){
        todoRepo = Mockito.mock(TodoRepository.class);
        service = new DeleteTodoService(todoRepo);
    }


    @Test
    void remove(){
//        CreateTodoService.Requirement requirement = new CreateTodoService.Requirement("test");
//        Todo todo = new Todo(requirement.content());
//        ReflectionTestUtils.setField(todo, "id", 1L);
        //ㄴ> 테스트를 위해 다른 테스팅에 있는 걸 끌어와서 써도 되는가? [CreateTodoService]

        //given
        Todo todo = new Todo("test");

        //when
        BDDMockito.when(todoRepo.findById(BDDMockito.anyLong()))
                .thenReturn(Optional.of(todo)); //객체를 찾아서 반환
        //ㄴ> 얘로 todo 객체를 반환해야 deleteAt을 조회할 수 있음
        service.remove(1L);

        //then
        assertThat(todo.getDeletedAt()).isNotNull();
    }

    /*
    @Test
    void remove() {[의문1]//이거는 필드비교가 아니라 객체비교 아님??????
                   //remove() 하고 반환되는 객체가 null인지 아닌지 판별하는 메소드 되는 거 아님???
                   //필드비교가 되어야 하는 거 아님??????????????????
        //given
        Todo todo = new Todo("test");

        //when
        Throwable throwable = BDDAssertions
                .catchThrowable(() -> service.remove(1L));

        //then
        assertThat(throwable).isNotNull();
    } */

}