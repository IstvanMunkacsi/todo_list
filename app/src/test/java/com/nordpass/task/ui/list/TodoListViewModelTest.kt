package com.nordpass.task.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nordpass.tt.usecase.data.Todo
import com.nordpass.tt.usecase.todolist.get_todo_list.GetTodoListUseCaseContract
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset
import kotlin.random.Random

@RunWith(JUnit4::class)
class TodoListViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private lateinit var viewModel: TodoListViewModel


    @Before
    fun setUp() {
        val useCase = buildGetTodoListUseCaseMock()
        viewModel = TodoListViewModel(useCase)
    }

    @Test
    fun load_itemsSortedProperly() {
        viewModel.items.value?.let {
            for (i in 1 until it.size) {
                val previous = it[i - 1]
                val current = it[i]
                assert(!previous.isCompleted || current.isCompleted)

                if (previous.isCompleted != current.isCompleted) continue

                val previousDueOn = previous.dueOn
                val currentDueOn = current.dueOn ?: continue
                assert(previousDueOn != null)

                if (currentDueOn.isAfter(previousDueOn)) continue
                if (currentDueOn.isEqual(previousDueOn)) continue

                assert(false)
            }
        }
    }

    private fun buildGetTodoListUseCaseMock(): GetTodoListUseCaseContract {
        return object : GetTodoListUseCaseContract {
            override fun observe(): Flowable<List<Todo>> {
                return Flowable.fromCallable {
                    (0..100).map { randomTodo() }
                }
            }
        }
    }

    private fun randomTodo(): Todo {
        val year = Random.nextInt(2020, 2023)
        val month = Random.nextInt(1, 12)
        val day = Random.nextInt(1, 28)
        val hour = Random.nextInt(0, 23)
        val minute = Random.nextInt(0, 59)
        val offsetDateTime = OffsetDateTime.of(
            year,
            month,
            day,
            hour,
            minute,
            0,
            0,
            ZoneOffset.ofHoursMinutes(5, 30)
        )

        return Todo(1, "", Random.nextBoolean(), "", offsetDateTime)
    }
}