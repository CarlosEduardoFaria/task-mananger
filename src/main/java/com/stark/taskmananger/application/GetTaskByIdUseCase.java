package com.stark.taskmananger.application;

import com.stark.taskmananger.application.output.TaskOutput;
import com.stark.taskmananger.domain.TaskId;
import com.stark.taskmananger.domain.TaskRepository;
import com.stark.taskmananger.domain.exception.TaskNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetTaskByIdUseCase {

    private final TaskRepository taskRepository;

    public GetTaskByIdUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskOutput execute (TaskId id) {
        return taskRepository.findById(id)
                .map(TaskOutput::from)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
}
