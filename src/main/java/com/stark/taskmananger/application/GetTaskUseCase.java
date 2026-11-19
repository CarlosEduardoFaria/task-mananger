package com.stark.taskmananger.application;

import com.stark.taskmananger.application.output.TaskOutput;
import com.stark.taskmananger.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTaskUseCase {

    private final TaskRepository taskRepository;

    public GetTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskOutput> execute () {
        return taskRepository.findAll()
                .stream()
                .map(TaskOutput::from)
                .toList();
    }
}
