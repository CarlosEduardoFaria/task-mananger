package com.stark.taskmananger.infrastructure.repository;

import com.stark.taskmananger.domain.*;
import org.junit.jupiter.api.DisplayName;

@DisplayName("In Memory Task Repository Implementation Test")
class InMemoryTaskRepositoryImplTest extends TaskRepositoryTest {

    @Override
    protected TaskRepository createRepository() {
        return new InMemoryTaskRepositoryImpl();
    }
}