package younah.TodoRefactor.domain.todo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import younah.TodoRefactor.domain.todo.entity.Todo;
import younah.TodoRefactor.domain.todo.repository.TodoRepository;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
        Todo todo = new Todo("createdContent"); //처음에 만들어져있는 객체

        BDDMockito.when(todoRepo.findById(BDDMockito.anyLong()))
                .thenReturn(Optional.of(todo)); //어떤 Long 값을 넣든 일단 todo를 반환할거임

        UpdateTodoService.Requirement requirement = new UpdateTodoService.Requirement("updateContent");
        //ㄴ> 외부에서 받아오는 requirement 만들어서 그거 로직에 직접 쓰기

        //when
        service.update(1L, requirement);

        //then
        assertThat(todo.getContent()).isEqualTo("updateContent");

    }

}