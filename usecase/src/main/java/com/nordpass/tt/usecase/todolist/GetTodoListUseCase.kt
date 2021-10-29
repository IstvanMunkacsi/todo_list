package com.nordpass.tt.usecase.todolist

import com.nordpass.tt.usecase.Todo
import io.reactivex.Single
import javax.inject.Inject

class GetTodoListUseCase @Inject constructor(
    private val storage: TodoStorage
) {
    fun get(): Single<List<Todo>> {
        return storage.getAll()
    }

    fun getSortedByDue(): Single<List<Todo>> {
        return get().map { list ->
            list.sortedWith(
                compareBy<Todo> { todo -> todo.isCompleted }.thenBy { todo -> todo.dueOn }
            )
        }
    }
}