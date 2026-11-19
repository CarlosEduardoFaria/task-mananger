package com.stark.taskmananger.infrastructure.repository;

import com.stark.taskmananger.domain.Task;
import com.stark.taskmananger.domain.TaskId;
import com.stark.taskmananger.domain.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryTaskRepositoryImpl implements TaskRepository {

    private Map<TaskId, Task> storage = new HashMap<>();

    @Override
    public Task save(Task task) {
        storage.put(task.getId(), task);
        return task;
    }

    @Override
    public List<Task> findAll() {
        return storage.values().stream().toList();
    }

    @Override
    public Optional<Task> findById(TaskId id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void delete(Task task) {
        storage.remove(task.getId());
    }
}
