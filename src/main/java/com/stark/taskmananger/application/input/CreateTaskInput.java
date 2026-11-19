package com.stark.taskmananger.application.input;

import java.util.Optional;

public record CreateTaskInput(
        String title,
        Optional<String> description
) {
}
