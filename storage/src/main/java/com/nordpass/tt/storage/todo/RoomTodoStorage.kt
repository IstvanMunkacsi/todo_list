package com.nordpass.tt.storage.todo

import com.nordpass.tt.usecase.data.Todo
import com.nordpass.tt.usecase.common.Io
import com.nordpass.tt.usecase.todolist.TodoStorage
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

internal class RoomTodoStorage @Inject constructor(
    private val dao: TodoDao,
    private val mapper: TodoMapper,
    @Io private val scheduler: Scheduler
) : TodoStorage {

    override fun save(todoList: List<Todo>): Completable {
        return dao.updateOrCreate(todoList.map(mapper::map))
            .subscribeOn(scheduler)
    }

    override fun update(todo: Todo): Completable {
        return dao.update(mapper.map(todo))
            .subscribeOn(scheduler)
    }

    override fun getAll(): Single<List<Todo>> {
        return dao.getAll()
            .map { list -> list.map(mapper::map) }
            .subscribeOn(scheduler)
    }

    override fun observeAll(): Flowable<List<Todo>> {
        return dao.observeAll()
            .map { list -> list.map(mapper::map) }
            .subscribeOn(scheduler)
    }

    override fun getById(id: Int): Single<Todo> {
        return dao.getById(id)
            .map(mapper::map)
            .subscribeOn(scheduler)
    }

    override fun observeById(id: Int): Flowable<Todo> {
        return dao.observeById(id)
            .map(mapper::map)
            .subscribeOn(scheduler)
    }

    override fun removeById(ids: List<Int>): Completable {
        return dao.remove(ids)
            .subscribeOn(scheduler)
    }
}