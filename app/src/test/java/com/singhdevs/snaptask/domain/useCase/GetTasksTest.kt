package com.singhdevs.snaptask.domain.useCase

import com.google.common.truth.Truth.assertThat
import com.singhdevs.snaptask.data.repository.MockTaskRepository
import com.singhdevs.snaptask.domain.OrderType
import com.singhdevs.snaptask.domain.TaskOrder
import com.singhdevs.snaptask.domain.model.Task
import com.singhdevs.snaptask.domain.model.TaskPriority
import com.singhdevs.snaptask.domain.model.TaskStatus
import com.singhdevs.snaptask.domain.useCases.GetTasks
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetTasksTest {

    private lateinit var getTasks: GetTasks
    private lateinit var fakeRepository: MockTaskRepository

    @Before
    fun setUp() {
        fakeRepository = MockTaskRepository()
        getTasks = GetTasks(fakeRepository)

        var tasksToInsert = listOf(
            Task(
                title = "Task A",
                subtitle = "Subtitle A",
                date = 1L,
                priority = TaskPriority.URGENT,
                status = TaskStatus.NOT_STARTED,
                description = "j",
                id = 1
            ),
            Task(
                title = "Task B",
                subtitle = "Subtitle B",
                date = 2L,
                priority = TaskPriority.IMPORTANT,
                status = TaskStatus.IN_PROGRESS,
                description = "i",
                id = 2
            ),
            Task(
                title = "Task C",
                subtitle = "Subtitle C",
                date = 3L,
                priority = TaskPriority.NORMAL,
                status = TaskStatus.COMPLETED,
                description = "h",
                id = 3
            ),
            Task(
                title = "Task D",
                subtitle = "Subtitle D",
                date = 4L,
                priority = TaskPriority.LOW,
                status = TaskStatus.NOT_STARTED,
                description = "g",
                id = 4
            ),
            Task(
                title = "Task E",
                subtitle = "Subtitle E",
                date = 5L,
                priority = TaskPriority.URGENT,
                status = TaskStatus.IN_PROGRESS,
                description = "f",
                id = 5
            ),
            Task(
                title = "Task F",
                subtitle = "Subtitle F",
                date = 6L,
                priority = TaskPriority.IMPORTANT,
                status = TaskStatus.COMPLETED,
                description = "e",
                id = 6
            ),
            Task(
                title = "Task G",
                subtitle = "Subtitle G",
                date = 7L,
                priority = TaskPriority.NORMAL,
                status = TaskStatus.NOT_STARTED,
                description = "d",
                id = 7
            ),
            Task(
                title = "Task H",
                subtitle = "Subtitle H",
                date = 8L,
                priority = TaskPriority.LOW,
                status = TaskStatus.IN_PROGRESS,
                description = "c",
                id = 8
            ),
            Task(
                title = "Task I",
                subtitle = "Subtitle I",
                date = 9L,
                priority = TaskPriority.URGENT,
                status = TaskStatus.COMPLETED,
                description = "b",
                id = 9
            ),
            Task(
                title = "Task J",
                subtitle = "Subtitle J",
                date = 10L,
                priority = TaskPriority.IMPORTANT,
                status = TaskStatus.NOT_STARTED,
                description = "a",
                id = 10
            )
        )

        tasksToInsert = tasksToInsert.shuffled()
        runBlocking {
            tasksToInsert.forEach { fakeRepository.addTask(it) }
        }
    }

    @Test
    fun `Order tasks by title ascending, correct order`() = runBlocking {
        val tasks = getTasks(TaskOrder.Title(OrderType.ASCENDING)).first()

        for(i in 0..tasks.size - 2) {
            assertThat(tasks[i].title).isLessThan(tasks[i+1].title)
        }
    }

    @Test
    fun `Order tasks by title descending, correct order`() = runBlocking {
        val tasks = getTasks(TaskOrder.Title(OrderType.DESCENDING)).first()

        for(i in 0..tasks.size - 2) {
            assertThat(tasks[i].title).isGreaterThan(tasks[i+1].title)
        }
    }

    @Test
    fun `Order tasks by date ascending, correct order`() = runBlocking {
        val tasks = getTasks(TaskOrder.Date(OrderType.ASCENDING)).first()

        for(i in 0..tasks.size - 2) {
            assertThat(tasks[i].date).isLessThan(tasks[i+1].date)
        }
    }

    @Test
    fun `Order tasks by date descending, correct order`() = runBlocking {
        val tasks = getTasks(TaskOrder.Date(OrderType.DESCENDING)).first()

        for(i in 0..tasks.size - 2) {
            assertThat(tasks[i].date).isGreaterThan(tasks[i+1].date)
        }
    }

    @Test
    fun `Order tasks by Priority ascending, correct order`() = runBlocking {
        val tasks = getTasks(TaskOrder.Priority(OrderType.ASCENDING)).first()

        for(i in 0..tasks.size - 2) {
            assertThat(tasks[i].priority).isLessThan(tasks[i+1].priority)
        }
    }

    @Test
    fun `Order tasks by Priority descending, correct order`() = runBlocking {
        val tasks = getTasks(TaskOrder.Priority(OrderType.DESCENDING)).first()

        for(i in 0..tasks.size - 2) {
            assertThat(tasks[i].priority).isGreaterThan(tasks[i+1].priority)
        }
    }

    @Test
    fun `Order tasks by Status ascending, correct order`() = runBlocking {
        val tasks = getTasks(TaskOrder.Status(OrderType.ASCENDING)).first()

        for(i in 0..tasks.size - 2) {
            assertThat(tasks[i].status).isLessThan(tasks[i+1].status)
        }
    }

    @Test
    fun `Order tasks by Status descending, correct order`() = runBlocking {
        val tasks = getTasks(TaskOrder.Status(OrderType.DESCENDING)).first()

        for(i in 0..tasks.size - 2) {
            assertThat(tasks[i].status).isGreaterThan(tasks[i+1].status)
        }
    }
}