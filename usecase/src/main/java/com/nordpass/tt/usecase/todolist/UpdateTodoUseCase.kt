package com.nordpass.tt.usecase.todolist

import com.nordpass.tt.usecase.data.Todo
import io.reactivex.Completable
import javax.inject.Inject

class UpdateTodoUseCase @Inject constructor(
    private val storage: TodoStorage
) {
    fun update(todo: Todo): Completable {
        return storage.update(todo)
    }
}