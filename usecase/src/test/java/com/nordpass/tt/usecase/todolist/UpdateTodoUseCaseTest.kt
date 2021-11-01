package com.nordpass.tt.usecase.todolist

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nordpass.tt.usecase.data.Todo
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test

class UpdateTodoUseCaseTest {
    private lateinit var useCase: UpdateTodoUseCase
    private val storage: TodoStorage = mock()

    @Before
    fun setup() {
        useCase = UpdateTodoUseCase(storage)
    }

    @Test
    fun update_itemUpdated() {
        val todo: Todo = mock()
        given(storage.update(todo)).willReturn(Completable.complete())

        useCase.update(todo)
            .test()
            .assertOf { verify(storage).update(todo) }
            .assertComplete()
            .assertNoErrors()
    }
}