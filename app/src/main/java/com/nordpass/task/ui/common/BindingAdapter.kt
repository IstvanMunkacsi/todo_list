package com.nordpass.task.ui.common

import android.widget.EditText
import androidx.annotation.StringRes
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object BindingAdapter {

    @BindingAdapter("cancelErrorEditTextView", "errorText", requireAll = true)
    @JvmStatic
    fun setError(
        textInputLayout: TextInputLayout,
        editText: EditText,
        @StringRes errorText: Int?
    ) {
        if (errorText == null) {
            textInputLayout.isErrorEnabled = false
            return
        }

        textInputLayout.isErrorEnabled = true
        textInputLayout.error = textInputLayout.context.getString(errorText)

        editText.doOnTextChanged { text, _, _, _ ->
            textInputLayout.isErrorEnabled =
                textInputLayout.isErrorEnabled && text.isNullOrEmpty()
        }
    }
}