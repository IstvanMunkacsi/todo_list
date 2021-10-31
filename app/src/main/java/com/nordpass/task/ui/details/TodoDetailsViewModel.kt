package com.nordpass.task.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.nordpass.task.ui.base.BaseViewModel
import com.nordpass.tt.usecase.Todo
import com.nordpass.tt.usecase.common.Time
import com.nordpass.tt.usecase.todolist.UpdateTodoUseCase
import io.reactivex.rxkotlin.subscribeBy

class TodoDetailsViewModel @ViewModelInject constructor(
    private val updateTodoUseCase: UpdateTodoUseCase,
) : BaseViewModel() {

    val item = MutableLiveData<Todo>()
    val showEdit = MutableLiveData<Todo>()

    fun init(item: Todo) {
        this.item.value = item
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