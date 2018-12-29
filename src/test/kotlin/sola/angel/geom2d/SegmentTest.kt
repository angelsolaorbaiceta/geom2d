package sola.angel.geom2d

import org.junit.jupiter.api.Assertions.*
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
    fun `has height`() {
        assertEquals(
            300.0,
            Segment(Point.origin, Point(200.0, 300.0)).height
        )
    }

    @Test
    fun `has width`() {
        assertEquals(
            200.0,
            Segment(Point.origin, Point(200.0, 300.0)).width
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

    @Test
    fun `point is not close enough if out of bounds`() {
        assertFalse(
            segment.isCloseEnoughTo(Point(500.0, 500.0), 10.0)
        )
    }

    @Test
    fun `point which is inside bounds but not close enough`() {
        assertFalse(
            segment.isCloseEnoughTo(Point(250.0, 250.0), 10.0)
        )
    }

    @Test
    fun `point is close enough`() {
        assertTrue(
            segment.isCloseEnoughTo(Point(201.0, 201.0), 10.0)
        )
    }

    @Test
    fun `subdivide in exact parts`() {
        assertEquals(
            listOf(TParam.min, TParam.make(0.25), TParam.half, TParam.make(0.75), TParam.max),
            Segment(Point.origin, Point(10.0, 0.0)).subdivideInSegmentsWithLength(2.5)
        )
    }

    @Test
    fun `subdivide in parts`() {
        val positions = Segment(Point.origin, Point(10.0, 0.0)).subdivideInSegmentsWithLength(3.0)

        assertEquals(
            listOf(TParam.min, TParam.make(1.0/3.0), TParam.make(2.0/3.0), TParam.max),
            positions
        )
    }

    @Test
    fun `subdivide in parts including position`() {
        val positions = Segment(Point.origin, Point(10.0, 0.0))
            .subdivideInSegmentsWithLength(3.0, listOf(TParam.half))

        assertEquals(
            listOf(TParam.min, TParam.make(1.0/3.0), TParam.half, TParam.make(2.0/3.0), TParam.max),
            positions
        )
    }
}