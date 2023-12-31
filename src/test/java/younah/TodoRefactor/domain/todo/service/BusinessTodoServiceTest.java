package younah.TodoRefactor.domain.todo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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
        var todo = new Todo("test");
        ReflectionTestUtils.setField(todo, "id", 1L);
        given(todoRepo.findById(eq(1L)))
                .willReturn(Optional.of(todo));

        //when
        service.complete(1L);

        //then
        var argumentCaptor = ArgumentCaptor.forClass(Todo.class);
        verify(todoRepo, times(1))
                .save(argumentCaptor.capture());
        var capturedTodo = argumentCaptor.getValue();

        assertThat(capturedTodo.getStatus()).isEqualTo(Todo.TodoStatus.TODO_DONE);
    }

    @Test
    void withdraw() {

        //given
        var todo = new Todo("test");
        ReflectionTestUtils.setField(todo, "id", 1L);
        given(todoRepo.findById(eq(1L)))
                .willReturn(Optional.of(todo));

        //when
        service.withdraw(1L);

        //then
        var argumentCaptor = ArgumentCaptor.forClass(Todo.class);
        verify(todoRepo, times(1))
                .save(argumentCaptor.capture());
        var capturedTodo = argumentCaptor.getValue();

        assertThat(capturedTodo.getStatus()).isEqualTo(Todo.TodoStatus.TODO_BEFORE);
    }
}