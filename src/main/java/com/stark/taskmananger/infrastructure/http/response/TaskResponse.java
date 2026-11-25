package com.stark.taskmananger.infrastructure.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stark.taskmananger.application.output.TaskOutput;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TaskResponse (String id, String title, String description) {

    public static TaskResponse from (TaskOutput output) {
        return new TaskResponse(
                output.id(),
                output.title(),
                output.description().orElse(null));
    }
}
