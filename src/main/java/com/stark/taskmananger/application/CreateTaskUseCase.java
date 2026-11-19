package com.stark.taskmananger.application;

import com.stark.taskmananger.application.input.CreateTaskInput;
import com.stark.taskmananger.application.output.TaskOutput;
import com.stark.taskmananger.domain.Task;
import com.stark.taskmananger.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskUseCase {

    private final TaskRepository taskRepository;

    public CreateTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskOutput execute (CreateTaskInput input) {
        var task = new Task(input.title(), input.description());

        var saved = taskRepository.save(task);

        return TaskOutput.from(saved);
    }
}
