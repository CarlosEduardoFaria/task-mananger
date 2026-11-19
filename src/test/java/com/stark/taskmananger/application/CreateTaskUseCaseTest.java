package com.stark.taskmananger.application;

import com.stark.taskmananger.application.input.CreateTaskInput;
import com.stark.taskmananger.application.output.TaskOutput;
import com.stark.taskmananger.domain.Task;
import com.stark.taskmananger.domain.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("[CREATE] Task use case")
class CreateTaskUseCaseTest {

    @Mock
    TaskRepository repository;

    @InjectMocks
    CreateTaskUseCase useCase;

    @Test
    @DisplayName("Should create task successfully")
    void shold_create_task_sucefully() {
        // Given
        var input = new CreateTaskInput("Estudar Java", Optional.of("Finalizar o módulo de Records"));
        when(repository.save(any(Task.class))).thenAnswer( invocation -> invocation.getArgument(0));

        // When
        TaskOutput output = useCase.execute(input);

        // Then
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals("Estudar Java", output.title());
        assertEquals(Optional.of("Finalizar o módulo de Records"), output.description());

        verify(repository, times(1)).save(any(Task.class));
    }
}