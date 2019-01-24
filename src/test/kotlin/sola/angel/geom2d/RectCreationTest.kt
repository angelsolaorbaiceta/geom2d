package sola.angel.geom2d

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RectCreationTest {
    private val points = listOf(
        Point(1.0, 3.0),
        Point(2.0, 5.0),
        Point(3.0, 4.0)
    )

    @Test
    fun `can be created containing points`() {
        val expected = Rect(Point(1.0, 3.0), Size.make(2.0, 2.0))
        val actual = Rect.makeContaining(points)

        assertEquals(expected, actual)
    }

    @Test
    fun `can be created containing points with margin`() {
        val expected = Rect(Point(0.0, 2.0), Size.make(4.0, 4.0))
        val actual = Rect.makeContaining(points, 1.0)

        assertEquals(expected, actual)
    }
}