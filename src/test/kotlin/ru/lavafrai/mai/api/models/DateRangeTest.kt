package ru.lavafrai.mai.api.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DateRangeTest {
    @Test
    fun `Test contains`() {
        assertTrue( DateRange.parse("12.02.2024 - 06.03.2024").contains(SerializableDate.parse("18.02.2024")) )
        assertTrue( DateRange.parse("12.02.2024 - 06.03.2024").contains(SerializableDate.parse("06.03.2024")) )
        assertFalse( DateRange.parse("12.02.2024 - 06.03.2024").contains(SerializableDate.parse("07.03.2024")) )
        assertFalse( DateRange.parse("12.02.2024 - 06.03.2024").contains(SerializableDate.parse("05.03.2025")) )
        assertFalse( DateRange.parse("12.02.2024 - 06.03.2024").contains(SerializableDate.parse("05.04.2025")) )
        assertFalse( DateRange.parse("12.02.2024 - 06.03.2024").contains(SerializableDate.parse("05.04.2024")) )
    }

    @Test
    fun `Test is now`() {
        assertTrue( DateRange(SerializableDate.now(), SerializableDate.now()).isNow() )
        assertTrue( DateRange(SerializableDate.parse("01.01.2000"), SerializableDate.now()).isNow() )
        assertFalse( DateRange(SerializableDate.parse("01.01.2000"), SerializableDate.parse("12.12.2001")).isNow() )
    }
}