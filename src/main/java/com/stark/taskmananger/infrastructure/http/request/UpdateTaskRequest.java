package com.stark.taskmananger.infrastructure.http.request;

import com.stark.taskmananger.application.input.UpdateTaskInput;
import com.stark.taskmananger.domain.TaskStatus;

import java.util.Optional;

public record UpdateTaskRequest (
        Optional<String> title,
        Optional<String> description,
        Optional<String> status
) {

    public UpdateTaskInput toInput() {
        return new UpdateTaskInput(title, description, status.map(TaskStatus::valueOf));
    }
}
