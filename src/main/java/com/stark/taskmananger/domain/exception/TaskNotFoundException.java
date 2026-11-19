package com.stark.taskmananger.domain.exception;

import com.stark.taskmananger.domain.TaskId;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(TaskId taskId) {
        super("Task not found with id: " + taskId);
    }
}
