package com.nordpass.task.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.nordpass.task.ui.base.BaseViewModel
import com.nordpass.task.ui.base.SingleLiveEvent
import com.nordpass.tt.usecase.data.Todo
import com.nordpass.tt.usecase.todolist.get_todo_list.GetTodoListUseCaseContract
import io.reactivex.rxkotlin.subscribeBy

class TodoListViewModel @ViewModelInject constructor(
    getTodoListUseCase: GetTodoListUseCaseContract
) : BaseViewModel() {
    val items = MutableLiveData<List<Todo>>()
    val showItem = SingleLiveEvent<Int>()

    init {
        getTodoListUseCase.observe()
            .subscribeBy(onNext = { items.postValue(it.sortByDue()) }, onError = ::handleError)
            .attach()
    }

    private fun List<Todo>.sortByDue(): List<Todo> {
        return sortedWith(
            compareBy<Todo> { todo -> todo.isCompleted }.thenBy { todo -> todo.dueOn }
        )
    }

    fun onItemClicked(todoId: Int) {
        showItem.postValue(todoId)
    }
}