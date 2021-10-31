package com.nordpass.tt.usecase.todolist.get_todo_list

import com.nordpass.tt.usecase.data.Todo
import io.reactivex.Flowable

interface GetTodoListUseCaseContract {
    fun observe(): Flowable<List<Todo>>
}