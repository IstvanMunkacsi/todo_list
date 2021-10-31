package com.nordpass.task.ui.edit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.nordpass.task.R
import com.nordpass.task.databinding.FragmentEditBinding
import com.nordpass.task.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoEditFragment : BaseFragment(R.layout.fragment_edit) {
    private val viewModel: TodoEditViewModel by viewModels()
    private val args: TodoEditFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.init(args.todo)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentEditBinding.bind(view).apply {
            lifecycleOwner = this@TodoEditFragment
            viewModel = this@TodoEditFragment.viewModel
        }
    }
}