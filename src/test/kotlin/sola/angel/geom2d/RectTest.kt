package sola.angel.geom2d

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class RectTest {

    @Test
    fun `two rectangles do not intersect`() {
        val rectA = Rect(Point.origin, Size(2.0, 3.0))
        val rectB = Rect(Point(5.0, 0.0), Size(2.0, 3.0))

        assertNull(
            rectA.intersectionWith(rectB)
        )
    }

    @Test
    fun `two rectangles intersection`() {
        val rectA = Rect(Point(2.0, 0.0), Size(3.0, 4.0))
        val rectB = Rect(Point(0.0, 2.0), Size(4.0, 4.0))
        val expectedIntersection = Rect(Point(2.0, 2.0), Size(2.0, 2.0))

        assertEquals(
            expectedIntersection,
            rectA.intersectionWith(rectB)
        )
    }

    @Test
    fun `make centered`() {
        val center = Point(3.0, 4.0)
        val size = Size(10.0, 20.0)

        assertEquals(
            Point(-2.0, -6.0),
            Rect.makeCentered(center, size).origin
        )
    }
}