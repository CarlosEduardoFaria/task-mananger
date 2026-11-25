package com.stark.taskmananger.infrastructure.http;

import com.stark.taskmananger.application.CreateTaskUseCase;
import com.stark.taskmananger.infrastructure.http.request.CreateTaskRequest;
import com.stark.taskmananger.infrastructure.http.response.TaskResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TaskResponse create (@RequestBody CreateTaskRequest request) {
        var input = request.toInput();
        var output = createTaskUseCase.execute(input);

        return TaskResponse.from(output);
    }
}
