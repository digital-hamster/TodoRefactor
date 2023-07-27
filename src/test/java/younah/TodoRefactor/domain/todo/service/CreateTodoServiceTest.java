package younah.TodoRefactor.domain.todo.service;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.repository.TodoRepository;


import static org.assertj.core.api.Assertions.assertThat;

class CreateTodoServiceTest {
    private CreateTodoService service;
    private TodoRepository todoRepo;

    @BeforeEach
    void setUp(){
        todoRepo = Mockito.mock(TodoRepository.class);
        service = new CreateTodoService(todoRepo);
    }

    @Test
    void succeess(){

        //given
        var requirement = new CreateTodoService.Requirement("test");
        var todo = new Todo(requirement.content());
        ReflectionTestUtils.setField(todo, "id", 1L);

        BDDMockito.given(todoRepo.save(Mockito.any(Todo.class)))
                .willReturn(todo);

        //when
        Throwable throwable = BDDAssertions
                .catchThrowable(() -> service.create(requirement));

        //then
        assertThat(throwable).isNull();
    }
}