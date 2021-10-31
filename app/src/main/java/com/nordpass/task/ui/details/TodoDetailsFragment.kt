package com.nordpass.task.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nordpass.task.R
import com.nordpass.task.databinding.FragmentDetailsBinding
import com.nordpass.task.ui.base.BaseFragment
import com.nordpass.task.ui.list.TodoListFragmentDirections
import com.nordpass.tt.usecase.Todo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoDetailsFragment : BaseFragment(R.layout.fragment_details) {
    private val viewModel: TodoDetailsViewModel by viewModels()
    private val args: TodoDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.init(args.todoId)
        }
        viewModel.showEdit.observe(this, Observer(::showTodoEdit))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentDetailsBinding.bind(view).apply {
            lifecycleOwner = this@TodoDetailsFragment
            viewModel = this@TodoDetailsFragment.viewModel
        }
    }

    private fun showTodoEdit(todo: Todo) {
        findNavController().navigate(TodoDetailsFragmentDirections.actionTodoEdit(todo))
    }
}