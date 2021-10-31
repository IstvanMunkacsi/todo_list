package com.nordpass.tt.usecase

import androidx.annotation.Keep
import com.nordpass.tt.usecase.common.Time
import com.nordpass.tt.usecase.common.Time.withCurrentOffsetSameInstant
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import java.io.Serializable

/** prevent class from obfuscation during release build
since it is used as typeArg via safeargs (in navigation graph) **/
@Keep
data class Todo(
    val id: Int,
    private var _title: String,
    private var _isCompleted: Boolean,
    private var _updatedAt: String,
    val dueOn: OffsetDateTime?,
) : Serializable {
    constructor(
        id: Int,
        title: String,
        isCompleted: Boolean,
        updatedAt: String,
        dueOn: String
    ) : this(
        id,
        title,
        isCompleted,
        updatedAt,
        Time.parseOrNull(dueOn, getDueOnFormatter())?.withCurrentOffsetSameInstant()
    )

    val title
        get() = _title

    fun setTitle(title: String) {
        _title = title
        fieldsUpdated()
    }

    val isCompleted
        get() = _isCompleted

    fun setIsCompleted(value: Boolean) {
        _isCompleted = value
        fieldsUpdated()
    }

    val updatedAt
        get() = _updatedAt

    private fun fieldsUpdated() {
        _updatedAt = Time.now()
    }

    val dueOnDateString
        get() = Time.formatOrNull(dueOn, getDueOnFormatter())

    val dueOnFormatted
        get() = Time.formatOrNull(dueOn, getDueOnRepresentationFormatter())

    companion object {
        private fun getDueOnFormatter(): DateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        private fun getDueOnRepresentationFormatter(): DateTimeFormatter =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)
    }
}