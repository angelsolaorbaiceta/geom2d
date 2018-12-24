package sola.angel.geom2d

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.PI

class PolygonTest {

    private val polygon = Polygon(listOf(
        Point(0.0, 0.0),
        Point(4.0, 0.0),
        Point(4.0, 2.0),
        Point(0.0, 2.0)
    ))

    @Test
    fun `geometrical center`() {
        assertEquals(
            Point(2.0, 1.0),
            polygon.geometricalCenter()
        )
    }

    @Test
    fun `rotated about a point`() {
        val center = Point(4.0, 0.0)
        val rotated = Polygon(listOf(
            Point(4.0, 4.0),
            Point(4.0, 0.0),
            Point(6.0, 0.0),
            Point(6.0, 4.0)
        ))

        assertEquals(
            rotated,
            polygon.rotateAboutPoint(-PI / 2.0, center)
        )
    }
}