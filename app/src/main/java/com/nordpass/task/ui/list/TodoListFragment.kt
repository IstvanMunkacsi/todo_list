package com.nordpass.task.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nordpass.task.R
import com.nordpass.task.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoListFragment : BaseFragment(R.layout.fragment_list) {
    private val viewModel: TodoListViewModel by viewModels()
    private var adapter: TodoListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TodoListAdapter(viewModel::onItemClicked)
        viewModel.items.observe(this, { adapter?.submitList(it) })
        viewModel.showItem.observe(this, Observer(::showTodoDetails))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.todoRecycler)?.adapter = adapter
    }

    private fun showTodoDetails(todoId: Int) {
        findNavController().navigate(TodoListFragmentDirections.actionTodoDetails(todoId))
    }
}