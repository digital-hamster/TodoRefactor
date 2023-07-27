package younah.TodoRefactor.domain.todo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.repository.TodoRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

        //given
        var todo = new Todo("test");
        ReflectionTestUtils.setField(todo, "id", 1L);
        given(todoRepo.findById(eq(1L)))
                .willReturn(Optional.of(todo));

        //when
        service.remove(1L);

        //then
        var argumentCaptor = ArgumentCaptor.forClass(Todo.class);
        verify(todoRepo, times(1))
                .save(argumentCaptor.capture()); //softDelete라서 save() 로직으로 캡쳐
        var capturedTodo = argumentCaptor.getValue();

        assertThat(capturedTodo.getDeletedAt()).isNotNull();
    }
}