package com.nordpass.task.ui.edit

import androidx.annotation.StringRes
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.nordpass.task.R
import com.nordpass.task.ui.base.BaseViewModel
import com.nordpass.tt.usecase.data.Todo
import com.nordpass.tt.usecase.todolist.UpdateTodoUseCase
import io.reactivex.rxkotlin.subscribeBy

class TodoEditViewModel @ViewModelInject constructor(
    private val updateTodoUseCase: UpdateTodoUseCase,
) : BaseViewModel() {

    val item = MutableLiveData<Todo>()
    val validationError = MutableLiveData<@StringRes Int>()

    fun init(item: Todo) {
        this.item.value = item
    }

    fun onSaveClicked(title: String) {
        val validationError = validateNewTitle(title)
        this.validationError.postValue(validationError?.errorMessage)
        if (validationError != null) return

        updateTitle(title)
    }

    private fun validateNewTitle(title: String): TitleValidationError? {
        if (title.trim().isEmpty()) return TitleValidationError.EMPTY

        return null
    }

    private fun updateTitle(title: String) {
        val todo = item.value?.copy()?.apply { setTitle(title) } ?: return
        updateTodoUseCase.update(todo)
            .subscribeBy(onComplete = { item.postValue(todo) }, onError = ::handleError)
            .attach()
    }

    enum class TitleValidationError(@StringRes val errorMessage: Int) {
        EMPTY(R.string.todoTitleEmptyError)
    }
}