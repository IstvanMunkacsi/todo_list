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

    private var item: Todo? = null
    var title: String = ""

    val validationError = MutableLiveData<@StringRes Int>()

    fun init(item: Todo) {
        this.item = item
        title = item.title
    }

    fun onTitleChanged(newTitle: CharSequence) {
        this.title = newTitle.toString()
    }

    fun onSaveClicked() {
        val validationError = validateNewTitle()
        this.validationError.postValue(validationError?.errorMessage)
        if (validationError != null) return

        updateTitle()
    }

    private fun validateNewTitle(): TitleValidationError? {
        if (title.trim().isEmpty()) return TitleValidationError.EMPTY

        return null
    }

    private fun updateTitle() {
        val newItem = item?.copy()?.apply { setTitle(this@TodoEditViewModel.title) } ?: return
        updateTodoUseCase.update(newItem)
            .subscribeBy(onComplete = { item = newItem }, onError = ::handleError)
            .attach()
    }

    enum class TitleValidationError(@StringRes val errorMessage: Int) {
        EMPTY(R.string.todoTitleEmptyError)
    }
}