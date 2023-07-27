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

class UpdateTodoServiceTest {
    private UpdateTodoService service;
    private TodoRepository todoRepo;

    @BeforeEach
    void setUp(){
        todoRepo = Mockito.mock(TodoRepository.class);
        service = new UpdateTodoService(todoRepo);
    }

    @Test
    void update() {

        //given
        Todo todo = new Todo("createdContent");
        ReflectionTestUtils.setField(todo, "id", 1L);
        given(todoRepo.findById(eq(1L)))
                .willReturn(Optional.of(todo));

        UpdateTodoService.Requirement requirement = new UpdateTodoService.Requirement("updateContent");

        //when
        service.update(1L, requirement);

        //then
        var argumentCaptor = ArgumentCaptor.forClass(Todo.class);
        verify(todoRepo, times(1))
                .save(argumentCaptor.capture());
        var capturedTodo = argumentCaptor.getValue();

        assertThat(capturedTodo.getContent()).isEqualTo("updateContent");
    }
}