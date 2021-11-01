package com.nordpass.task.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.nordpass.task.ui.base.BaseViewModel
import com.nordpass.task.ui.base.SingleLiveEvent
import com.nordpass.tt.usecase.data.Todo
import com.nordpass.tt.usecase.todolist.GetTodoItemUseCase
import com.nordpass.tt.usecase.todolist.UpdateTodoUseCase
import io.reactivex.rxkotlin.subscribeBy

class TodoDetailsViewModel @ViewModelInject constructor(
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val getTodoItemUseCase: GetTodoItemUseCase
) : BaseViewModel() {

    val item = MutableLiveData<Todo>()
    val showEdit = SingleLiveEvent<Todo>()

    fun init(todoId: Int) {
        getTodoItemUseCase.observe(todoId)
            .subscribeBy(onNext = item::postValue, onError = ::handleError)
            .attach()
    }

    fun onFinishedClicked() {
        updateCompleted(true)
    }

    fun onTodoClicked() {
        updateCompleted(false)
    }

    fun onEditClicked() {
        item.value?.let { showEdit.postValue(it) }
    }

    private fun updateCompleted(value: Boolean) {
        val todo = item.value?.copy()?.apply { setIsCompleted(value) } ?: return
        updateTodoUseCase.update(todo)
            .subscribeBy(onComplete = { item.postValue(todo) }, onError = ::handleError)
            .attach()
    }
}