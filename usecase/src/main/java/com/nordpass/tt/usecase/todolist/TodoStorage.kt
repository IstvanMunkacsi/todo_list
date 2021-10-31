package com.nordpass.tt.usecase.todolist

import com.nordpass.tt.usecase.data.Todo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface TodoStorage {
    fun save(todoList: List<Todo>): Completable

    fun update(todo: Todo): Completable

    fun getAll(): Single<List<Todo>>

    fun observeAll(): Flowable<List<Todo>>

    fun getById(id: Int): Single<Todo>

    fun observeById(id: Int): Flowable<Todo>

    fun removeById(ids: List<Int>): Completable
}