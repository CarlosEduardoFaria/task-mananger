package com.stark.taskmananger.application.input;

import com.stark.taskmananger.domain.TaskStatus;

import java.util.Optional;

public record UpdateTaskInput (
        Optional<String> title,
        Optional<String> description,
        Optional<TaskStatus> status
) {
}
