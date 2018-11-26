package sola.angel.geom2d

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PointTest {

    @Test
    fun `compute distance to another point`() {
        val p = Point(1.0, 2.0)
        val q = Point(4.0, 8.0)
        val expected = Math.sqrt(45.0)

        assertEquals(
            expected,
            p.distanceTo(q)
        )
    }

    @Test
    fun `interpolate at start point`() {
        val start = Point(1.0, 2.0)
        val end = Point(3.0, 6.0)

        assertEquals(
            start,
            start.interpolate(end, TParam.min)
        )
    }

    @Test
    fun `interpolate at end point`() {
        val start = Point(1.0, 2.0)
        val end = Point(3.0, 6.0)

        assertEquals(
            end,
            start.interpolate(end, TParam.max)
        )
    }

    @Test
    fun `interpolate between two points`() {
        val start = Point(1.0, 2.0)
        val end = Point(3.0, 6.0)
        val expected = Point(2.0, 4.0)

        assertEquals(
            expected,
            start.interpolate(end, TParam.half)
        )
    }

    @Test
    fun `displaced a vector a given number of times`() {
        val p = Point(1.0, 3.0)
        val v = Vector.make(4.0, 5.0)
        val expected = Point(9.0, 13.0)

        assertEquals(
            expected,
            p.displaced(v, 2.0)
        )
    }
}