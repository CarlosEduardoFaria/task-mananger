package com.stark.taskmananger.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class TaskRepositoryTest {

    TaskRepository repository;

    protected abstract TaskRepository createRepository();

    @BeforeEach
    void setUp() {
        this.repository = createRepository();
    }

    @Test
    @DisplayName("[SAVE] should save and retrieve task by id")
    void should_save_and_retrieve_task_by_id() {
        // Given
        var task = new Task("Passar na padaria", Optional.empty());

        //When
        var saved = repository.save(task);
        Optional<Task> result = repository.findById(task.getId());

        //Then
        assertThat(result).isPresent();
        assertThat(saved.getId()).isEqualTo(task.getId());
        assertThat(result.get().getDescription()).isEqualTo(task.getDescription());
        assertThat(saved.getStatus()).isEqualTo(task.getStatus());

    }

    @Test
    @DisplayName("[FIND] should find all persisted tasks")
    void shold_find_all_persisted_tasks() {

        //Given
        var task1 = new Task("Arrumar chuveiro", Optional.of("Comprar chuveiro novo"));
        var task2 = new Task("Trocar interruptor", Optional.of("Encontrar chave de fenda"));

        //When
        repository.save(task1);
        repository.save(task2);

        List<Task> tasks = repository.findAll();

        //Then
        assertThat(tasks).hasSize(2);
        assertThat(tasks).extracting(Task::getId).containsExactlyInAnyOrder(task1.getId(), task2.getId());
    }

    @Test
    @DisplayName("[DELETE] should delete task by id")
    void shold_delete_task_by_id() {
        //Given
        var task = repository.save(new Task("Treinar na academia", Optional.empty()));
        var taskId = task.getId();

        //When
        repository.delete(task);
        Optional<Task> result = repository.findById(taskId);

        //Then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("[EMPTY SEARCH] should return empty when searching non-existent task")
    void shold_return_empty_when_searching_non_existent_task () {
        //Given
        var nonExistentId = new TaskId();

        //When
        Optional<Task> result = repository.findById(nonExistentId);

        //Then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("[UPDATE] should update task status successfully")
    void shold_update_task_status_successfully () {
        //Given
        var task = repository.save(new Task("Atualizar CNH", Optional.empty()));
        task.setDescription(Optional.of("Não expirou ainda"));
        task.setStatus(TaskStatus.IN_PROGRESS);

        //When
        repository.save(task);
        Optional<Task> result = repository.findById(task.getId());

        //Then
        assertThat(result.get().getDescription()).isEqualTo(Optional.of("Não expirou ainda"));
        assertThat(result.get().getStatus()).isEqualTo(TaskStatus.IN_PROGRESS);
    }

}