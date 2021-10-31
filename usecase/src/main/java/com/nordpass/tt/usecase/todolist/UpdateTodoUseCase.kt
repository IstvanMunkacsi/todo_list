package com.nordpass.tt.usecase.todolist

import com.nordpass.tt.usecase.Todo
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UpdateTodoUseCase @Inject constructor(
    private val storage: TodoStorage
) {
    fun update(todo: Todo): Completable {
        return storage.update(todo)
    }
}