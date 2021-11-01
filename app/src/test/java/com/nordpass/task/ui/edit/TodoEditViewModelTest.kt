package com.nordpass.task.ui.edit

import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Test

class TodoEditViewModelTest {
    private lateinit var viewModel: TodoEditViewModel

    @Before
    fun setUp() {
        viewModel = TodoEditViewModel(mock())
    }

    @Test
    fun edit_validateTitleValidatorEmptyTitle() {
        viewModel.onTitleChanged("")
        val validationError = viewModel.validateNewTitle()
        assert(validationError == TodoEditViewModel.TitleValidationError.EMPTY)
    }

    @Test
    fun edit_validateTitleValidatorCorrectTitle() {
        viewModel.onTitleChanged("Some title")
        val validationError = viewModel.validateNewTitle()
        assert(validationError == null)
    }
}