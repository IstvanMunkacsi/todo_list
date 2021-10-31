package com.nordpass.tt.usecase.todolist

import com.nordpass.tt.usecase.data.Todo
import io.reactivex.Single

interface TodoListApi {
    fun get(): Single<List<Todo>>
}