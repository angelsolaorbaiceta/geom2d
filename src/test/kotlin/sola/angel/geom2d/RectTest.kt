package sola.angel.geom2d

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class RectTest {

    @Test
    fun `two rectangles do not intersect`() {
        val rectA = Rect(Point.origin, Size.make(2.0, 3.0))
        val rectB = Rect(Point(5.0, 0.0), Size.make(2.0, 3.0))

        assertNull(
            rectA.intersectionWith(rectB)
        )
    }

    @Test
    fun `two rectangles intersection`() {
        val rectA = Rect(Point(2.0, 0.0), Size.make(3.0, 4.0))
        val rectB = Rect(Point(0.0, 2.0), Size.make(4.0, 4.0))
        val expectedIntersection = Rect(Point(2.0, 2.0), Size.make(2.0, 2.0))

        assertEquals(
            expectedIntersection,
            rectA.intersectionWith(rectB)
        )
    }

    @Test
    fun `make centered`() {
        val center = Point(3.0, 4.0)
        val size = Size.make(10.0, 20.0)

        assertEquals(
            Point(-2.0, -6.0),
            Rect.makeCentered(center, size).origin
        )
    }

    @Test
    fun `make containing points and with margins`() {
        val rect = Rect.makeContaining(
            listOf(Point.origin, Point(10.0, 20.0)),
            5.0
        )

        assertEquals(
            Rect(Point(-5.0, -5.0), Size.make(20.0, 30.0)),
            rect
        )
    }

    @Test
    fun `including points which are inside original rect returns same rect`() {
        val originalRect = Rect(Point.origin, Size.make(10.0, 20.0))
        val rect = originalRect.includingPoints(listOf(Point(5.0, 5.0)))

        assertEquals(originalRect, rect)
    }

    @Test
    fun `including point to the right`() {
        val originalRect = Rect(Point.origin, Size.make(10.0, 20.0))
        val point = Point(15.0, 5.0)

        assertEquals(
            Rect(Point.origin, Size.make(15.0, 20.0)),
            originalRect.includingPoints(listOf(point))
        )
    }
}