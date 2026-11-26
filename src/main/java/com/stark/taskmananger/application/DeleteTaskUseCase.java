package com.stark.taskmananger.application;

import com.stark.taskmananger.domain.TaskId;
import com.stark.taskmananger.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaskUseCase {

    private final TaskRepository taskRepository;

    public DeleteTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void execute (TaskId id) {
        taskRepository.delete(id);
    }
}
