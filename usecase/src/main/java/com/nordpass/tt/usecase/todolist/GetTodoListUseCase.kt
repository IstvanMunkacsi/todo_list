package com.nordpass.tt.usecase.todolist

import com.nordpass.tt.usecase.data.Todo
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GetTodoListUseCase @Inject constructor(
    private val storage: TodoStorage
) {
    fun get(): Single<List<Todo>> {
        return storage.getAll()
    }

    fun observe(): Flowable<List<Todo>> {
        return storage.observeAll()
    }
}