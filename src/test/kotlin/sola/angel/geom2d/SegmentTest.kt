package sola.angel.geom2d

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SegmentTest {

    private val start = Point(400.0, 0.0)
    private val end = Point(0.0, 400.0)
    private val segment = Segment(start, end)

    @Test
    fun `has middle point`() {
        assertEquals(
            Point(200.0, 200.0),
            segment.middle
        )
    }

    @Test
    fun `has length`() {
        assertEquals(
            400.0 * Math.sqrt(2.0),
            segment.length
        )
    }

    @Test
    fun `compute middle point`() {
        assertEquals(
            Point(200.0, 200.0),
            segment.pointAt(TParam.half)
        )
    }

    @Test
    fun `closest point in middle`() {
        assertEquals(
            Point(200.0, 200.0),
            segment.closestPointTo(Point(50.0, 50.0))
        )
    }
}