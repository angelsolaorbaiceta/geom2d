package sola.angel.geom2d

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PolylineTest {

    private val pointOne = Point(5.0, 0.0)
    private val pointTwo = Point(10.0, 5.0)
    private val pointThree = Point(20.0, 0.0)
    private val polyline = Polyline(listOf(pointOne, pointTwo, pointThree))

    @Test
    fun `has stretches`() {
        assertEquals(
            listOf(
                Segment(pointOne, pointTwo),
                Segment(pointTwo, pointThree)
            ),
            polyline.stretches
        )
    }

    @Test
    fun `closest point to external point`() {
        assertEquals(
            pointThree,
            polyline.closestPointTo(Point(25.0, 0.0))
        )
    }
}