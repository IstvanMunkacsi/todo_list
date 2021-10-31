package com.nordpass.tt.usecase.todolist.get_todo_list

import com.nordpass.tt.usecase.data.Todo
import com.nordpass.tt.usecase.todolist.TodoStorage
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GetTodoListUseCase @Inject constructor(
    private val storage: TodoStorage
): GetTodoListUseCaseContract {

    override fun observe(): Flowable<List<Todo>> {
        return storage.observeAll()
    }
}