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
        CreateTodoService.Requirement requirement = new CreateTodoService.Requirement("test");
        Todo todo = new Todo(requirement.content());
        ReflectionTestUtils.setField(todo, "id", 1L);

        BDDMockito.given(todoRepo.save(Mockito.any(Todo.class)))
                .willReturn(todo);
        //ㄴ> 로직이 돌 때 실제 사용되는 Repository 메소드를 정의해놓은듯

        //when
        Throwable throwable = BDDAssertions
                .catchThrowable(() -> service.create(requirement));
        //ㄴ> 예외를 발생시키는 방법

        //then
        assertThat(throwable).isNull();
    }


}