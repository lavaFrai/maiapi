package ru.lavafrai.mai.api.models

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import ru.lavafrai.mai.api.models.time.Date

class SerializableDateTest {

    @Test
    fun `Later than test`() {
        assertTrue( Date.now().isLaterThan(Date.parse("01.01.2000")) )
        assertFalse( Date.now().isLaterThan(Date.parse("01.01.3000")) )
    }

    @Test
    fun `Earlier than test`() {
        assertFalse( Date.now().isEarlierThan(Date.parse("01.01.2000")) )
        assertTrue( Date.now().isEarlierThan(Date.parse("01.01.3000")) )

    }

    @Test
    fun `Same test`() {
        assertTrue(Date.now().isSame(Date.now()))
        assertFalse(Date.now().isSame(Date.parse("01.01.3000")))
        assertTrue(Date.parse("01.01.3000").isSame(Date.parse("01.01.3000")))
    }

    @Test
    fun `Mai format test`() {
        assertTrue(Date.parseMaiFormat("20230107").isSame(Date.parse("07.01.2023")))
        println(Date.parseMaiFormat("20230107"))
    }

    @Test
    fun `Get week test`() {
        val week = Date.parse("16.03.2024").getWeek()
        println(week)
        assertTrue(week.startDate == Date.parse("11.03.2024"))
        assertTrue(week.endDate == Date.parse("17.03.2024"))
    }
}