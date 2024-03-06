package ru.lavafrai.mai.api.models

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SerializableDateTest {

    @Test
    fun `Later than test`() {
        assertTrue( SerializableDate.now().isLaterThan(SerializableDate.parse("01.01.2000")) )
        assertFalse( SerializableDate.now().isLaterThan(SerializableDate.parse("01.01.3000")) )
    }

    @Test
    fun `Earlier than test`() {
        assertFalse( SerializableDate.now().isEarlierThan(SerializableDate.parse("01.01.2000")) )
        assertTrue( SerializableDate.now().isEarlierThan(SerializableDate.parse("01.01.3000")) )

    }

    @Test
    fun `Same test`() {
        assertTrue(SerializableDate.now().isSame(SerializableDate.now()))
        assertFalse(SerializableDate.now().isSame(SerializableDate.parse("01.01.3000")))
        assertTrue(SerializableDate.parse("01.01.3000").isSame(SerializableDate.parse("01.01.3000")))
    }
}