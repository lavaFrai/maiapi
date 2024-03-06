package ru.lavafrai.mai.api.models

import org.junit.jupiter.api.Test
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.parser.parseGroupsList
import ru.lavafrai.mai.api.parser.parseSchedule
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ScheduleParserTest {
    @Test
    fun `Groups list test`() {
        val groups = parseGroupsList()
        assertTrue(groups.isNotEmpty())
        println(groups)
    }

    @Test
    fun `Group parse test`() {
        /*val groups = parseGroupsList()
        assertTrue(groups.isNotEmpty())
        val schedule = parseSchedule(groups[0])*/
        val schedule = parseSchedule(Group("М4О-106Б-23"))
        assertNotNull(schedule)
        println(schedule)
    }


}