package sola.angel.geom2d

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PointOrderTest {

    @Test
    fun `points with same position have equal ordering`() {
        val point = Point(3.0, 5.0)
        Assertions.assertEquals(0, point.compareTo(point))
    }

    @Test
    fun `node with smaller x goes first`() {
        val pointA = Point(2.0, 3.0)
        val pointB = Point(3.0, 3.0)

        Assertions.assertEquals(-1, pointA.compareTo(pointB))
    }

    @Test
    fun `node with smaller y goes first (if x are equal)`() {
        val pointA = Point(2.0, 3.0)
        val pointB = Point(2.0, 5.0)

        Assertions.assertEquals(-1, pointA.compareTo(pointB))
    }
}