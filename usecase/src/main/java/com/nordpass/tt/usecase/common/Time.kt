package com.nordpass.tt.usecase.common

import org.threeten.bp.*
import org.threeten.bp.format.DateTimeFormatter

object Time {
    fun now() = OffsetDateTime.now().toString()

    private fun currentZoneOffset(): ZoneOffset {
        val zoneId = ZoneId.systemDefault()
        return zoneId.rules.getOffset(Instant.now())
    }

    fun parseOrNull(
        value: String?,
        formatter: DateTimeFormatter,
        matchCurrentOffset: Boolean = false
    ): OffsetDateTime? {
        return tryOrNull {
            val date = OffsetDateTime.parse(value, formatter)
            if (matchCurrentOffset) return@tryOrNull date?.withCurrentOffsetSameInstant()

            return@tryOrNull date
        }
    }

    fun formatOrNull(value: OffsetDateTime?, formatter: DateTimeFormatter): String? {
        return tryOrNull { value?.format(formatter) }
    }

    private fun <R> tryOrNull(func: () -> R): R? {
        return try {
            func()
        } catch (e: DateTimeException) {
            null
        }
    }

    private fun OffsetDateTime.withCurrentOffsetSameInstant(): OffsetDateTime {
        return withOffsetSameInstant(currentZoneOffset())
    }
}