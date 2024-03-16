package ru.lavafrai.mai.api.models

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.parser.parseGroupsList
import ru.lavafrai.mai.api.parser.parseSchedule
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ScheduleTest {
    @Test
    fun `Groups list test`() {
        val groups = parseGroupsList()
        assertTrue(groups.isNotEmpty())
        println(groups)
    }

    @Test
    fun `Schedule parse test`() {
        /*val groups = parseGroupsList()
        assertTrue(groups.isNotEmpty())
        val schedule = parseSchedule(groups[0])*/
        val schedule = parseSchedule(Group("М4О-106Б-23"))
        assertNotNull(schedule)
        println(Json.encodeToString(schedule))
    }

    /*@Test
    fun `All groups test`() {
        val groups = Api.getGroups()
        val i = AtomicInteger(280)
        val schedules: MutableList<Schedule?> = mutableListOf()

        while (i.get() < groups.size) {
            schedules.add(parseSchedule(groups[i.get()]))
            println("${i.incrementAndGet()}/${groups.size}")
        }

        println(Json.encodeToString(schedules))
    }*/

    @Test
    fun `Get weeks test`() {
        val schedule = parseSchedule(Group("М4О-106Б-23"))
        assertNotNull(schedule)
        println(schedule.getWeeks())
    }

    @Test
    fun `Get current week test`() {
        val schedule = parseSchedule(Group("М4О-106Б-23"))
        assertNotNull(schedule)
        println(Json.encodeToString(schedule.getCurrentWeekSchedule()))
    }
}

fun <T, R> Iterable<T>.mapThreaded(
    numThreads: Int = 12,
    exec: ExecutorService = Executors.newFixedThreadPool(numThreads),
    transform: (T) -> R): List<R> {

    // default size is just an inlined version of kotlin.collections.collectionSizeOrDefault
    val defaultSize = if (this is Collection<*>) this.size else 10
    val destination = Collections.synchronizedList(ArrayList<R>(defaultSize))

    for (item in this) {
        exec.submit { destination.add(transform(item)) }
    }

    exec.shutdown()
    exec.awaitTermination(1, TimeUnit.DAYS)

    return ArrayList<R>(destination)
}