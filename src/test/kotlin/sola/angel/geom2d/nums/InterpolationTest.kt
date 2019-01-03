package sola.angel.geom2d.nums

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import sola.angel.nums.interpolateValues

class InterpolationTest {

    @Test
    fun `interpolate values`() {
        assertEquals(
            listOf(1.0, 1.25, 1.5, 1.75, 2.0),
            interpolateValues(1.0, 2.0, 4)
        )
    }
}