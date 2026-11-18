package com.stark.taskmananger.domain;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Task save(Task task);
    List<Task> findAll();
    Optional<Task> findById(TaskId id);
    void delete(Task task);
}
