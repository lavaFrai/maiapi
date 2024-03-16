package ru.lavafrai.mai.api.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.lavafrai.mai.api.models.time.Date

class DateRangeTest {
    @Test
    fun `Test contains`() {
        assertTrue( DateRange.parse("12.02.2024 - 06.03.2024").contains(Date.parse("18.02.2024")) )
        assertTrue( DateRange.parse("12.02.2024 - 06.03.2024").contains(Date.parse("06.03.2024")) )
        assertFalse( DateRange.parse("12.02.2024 - 06.03.2024").contains(Date.parse("07.03.2024")) )
        assertFalse( DateRange.parse("12.02.2024 - 06.03.2024").contains(Date.parse("05.03.2025")) )
        assertFalse( DateRange.parse("12.02.2024 - 06.03.2024").contains(Date.parse("05.04.2025")) )
        assertFalse( DateRange.parse("12.02.2024 - 06.03.2024").contains(Date.parse("05.04.2024")) )
    }

    @Test
    fun `Test is now`() {
        assertTrue( DateRange(Date.now(), Date.now()).isNow() )
        assertTrue( DateRange(Date.parse("01.01.2000"), Date.now()).isNow() )
        assertFalse( DateRange(Date.parse("01.01.2000"), Date.parse("12.12.2001")).isNow() )
    }
}