package com.nordpass.tt.usecase

import com.nordpass.tt.usecase.common.Time
import com.nordpass.tt.usecase.common.Time.withCurrentOffsetSameInstant
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import java.io.Serializable

data class Todo(
    val id: Int,
    val title: String,
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