package com.softwarefactory.exampleproject4

import com.softwarefactory.exampleproject4.ui.formatTime
import java.time.LocalTime
import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun timeFormatterUsesTwentyFourHourPattern() {
        assertEquals("09:05:07", formatTime(LocalTime.of(9, 5, 7)))
        assertEquals("23:59:59", formatTime(LocalTime.of(23, 59, 59)))
        assertEquals("00:00:00", formatTime(LocalTime.MIDNIGHT))
    }
}
