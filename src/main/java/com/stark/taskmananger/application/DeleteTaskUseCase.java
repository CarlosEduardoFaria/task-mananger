package com.stark.taskmananger.application;

import com.stark.taskmananger.domain.Task;
import com.stark.taskmananger.domain.TaskRepository;
import com.stark.taskmananger.domain.exception.TaskNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaskUseCase {

    private final TaskRepository taskRepository;

    public DeleteTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void execute (Task task) {
        if (taskRepository.findById(task.getId()).isEmpty()) {
            throw new TaskNotFoundException(task.getId());
        }
        taskRepository.delete(task);
    }
}
