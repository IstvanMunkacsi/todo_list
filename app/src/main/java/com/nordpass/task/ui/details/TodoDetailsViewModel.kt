package com.nordpass.task.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.nordpass.task.ui.base.BaseViewModel
import com.nordpass.tt.usecase.Todo
import com.nordpass.tt.usecase.common.Time

class TodoDetailsViewModel @ViewModelInject constructor() : BaseViewModel() {

    val item = MutableLiveData<Todo>()

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
        //todo
    }

    private fun updateCompleted(value: Boolean) {
        item.value = item.value?.apply { setIsCompleted(value) }
    }
}