package sola.angel.geom2d

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RectContainsPointTest {
    private val rect = Rect(Point.origin, Size(2.0, 5.0))

    @Test
    fun `rect contains point`() {
        assertTrue(
            rect.containsPoint(Point(1.0, 4.0))
        )
    }

    @Test
    fun `rect does not contain point to the left`() {
        assertFalse(
            rect.containsPoint(Point(-1.0, 4.0))
        )
    }

    @Test
    fun `rect does not contain point to the right`() {
        assertFalse(
            rect.containsPoint(Point(3.0, 4.0))
        )
    }

    @Test
    fun `rect does not contain point below it`() {
        assertFalse(
            rect.containsPoint(Point(1.0, -1.0))
        )
    }

    @Test
    fun `rect does not contain point on top of it`() {
        assertFalse(
            rect.containsPoint(Point(1.0, 6.0))
        )
    }
}