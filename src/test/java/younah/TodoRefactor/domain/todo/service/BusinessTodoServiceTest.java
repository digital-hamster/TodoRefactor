package younah.TodoRefactor.domain.todo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.repository.TodoRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;



class BusinessTodoServiceTest {

    private BusinessTodoService service;
    private TodoRepository todoRepo;

    @BeforeEach
    void setUp(){
        todoRepo = Mockito.mock(TodoRepository.class);
        service = new BusinessTodoService(todoRepo);
    }

    @Test
    void complete() {
        //given
        Todo todo = new Todo("test");

        //when
        BDDMockito.given(todoRepo.findById(BDDMockito.anyLong()))
                .willReturn(Optional.of(todo));
        service.complete(1L);

        //then
        assertThat(todo.getStatus()).isEqualTo(Todo.TodoStatus.TODO_DONE);
        //ㄴ> complete는 null로 확인할 수 있는 게 아닌 거 같아서 요 방식으로 했어요

    }

    @Test
    void withdraw() {
        //given
        Todo todo = new Todo("test");

        //when
        BDDMockito.given(todoRepo.findById(BDDMockito.anyLong()))
                .willReturn(Optional.of(todo));
        service.withdraw(1L);

        //then
        assertThat(todo.getStatus()).isEqualTo(Todo.TodoStatus.TODO_BEFORE);
    }
}