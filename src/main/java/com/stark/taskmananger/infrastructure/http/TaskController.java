package com.stark.taskmananger.infrastructure.http;

import com.stark.taskmananger.application.*;
import com.stark.taskmananger.domain.TaskId;
import com.stark.taskmananger.infrastructure.http.request.CreateTaskRequest;
import com.stark.taskmananger.infrastructure.http.request.UpdateTaskRequest;
import com.stark.taskmananger.infrastructure.http.response.TaskResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final GetTaskUseCase getTaskUseCase;
    private final GetTaskByIdUseCase getTaskByIdUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase, GetTaskUseCase getTaskUseCase, GetTaskByIdUseCase getTaskByIdUseCase, DeleteTaskUseCase deleteTaskUseCase, UpdateTaskUseCase updateTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.getTaskUseCase = getTaskUseCase;
        this.getTaskByIdUseCase = getTaskByIdUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
    }

    @GetMapping
    List<TaskResponse> listTasks() {
        return getTaskUseCase.execute().stream().map(TaskResponse::from).toList();
    }

    @GetMapping("/{id}")
    TaskResponse findTask(@PathVariable UUID id) {
        var output = getTaskByIdUseCase.execute(new TaskId(id));
        return TaskResponse.from(output);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TaskResponse createTask (@Valid @RequestBody CreateTaskRequest request) {
        var input = request.toInput();
        var output = createTaskUseCase.execute(input);

        return TaskResponse.from(output);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    TaskResponse updateTask(@PathVariable UUID id, @RequestBody UpdateTaskRequest request) {
        var input = request.toInput();
        var output = updateTaskUseCase.execute(new TaskId(id), input);

        return TaskResponse.from(output);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTask(@PathVariable UUID id) {
        deleteTaskUseCase.execute(new TaskId(id));
    }
}
