package com.nordpass.tt.usecase.core

import com.nordpass.tt.usecase.todolist.TodoStorage
import com.nordpass.tt.usecase.todolist.get_todo_list.GetTodoListUseCase
import com.nordpass.tt.usecase.todolist.get_todo_list.GetTodoListUseCaseContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
class UseCaseModule {
    @Provides
    fun provideGetTodoListUseCase(storage: TodoStorage): GetTodoListUseCaseContract {
        return GetTodoListUseCase(storage)
    }
}