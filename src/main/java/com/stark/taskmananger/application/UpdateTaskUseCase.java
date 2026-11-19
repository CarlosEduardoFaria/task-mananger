package com.stark.taskmananger.application;

import com.stark.taskmananger.application.input.UpdateTaskInput;
import com.stark.taskmananger.application.output.TaskOutput;
import com.stark.taskmananger.domain.TaskId;
import com.stark.taskmananger.domain.TaskRepository;
import com.stark.taskmananger.domain.exception.TaskNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UpdateTaskUseCase {

    private final TaskRepository taskRepository;

    public UpdateTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskOutput execute (TaskId taskId, UpdateTaskInput input) {
        var task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        task.update(input.title(), input.description(), input.status());
        var updated = taskRepository.save(task);
        return TaskOutput.from(updated);
    }
}
