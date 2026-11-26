package com.stark.taskmananger.infrastructure.http.request;

import com.stark.taskmananger.application.input.CreateTaskInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public record CreateTaskRequest(
        @NotBlank
        @Size(min = 3, max = 100, message = "O campo `title` deve ter entre 3 e 100 caracteres")
        String title,

        Optional<@Size(max = 500, message = "O campo `description` deve ter no máximo 500 caracteres") String> description) {

    public CreateTaskInput toInput() {
        return new CreateTaskInput(title, description);
    }
}
