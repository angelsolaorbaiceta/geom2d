package sola.angel.geom2d

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AffineTransformTest {

    @Test
    fun `identity transform on point`() {
        val point = Point(3.4, 5.0)

        assertEquals(
            point,
            AffineTransform.identity.apply(point)
        )
    }

    @Test
    fun `displace point`() {
        val point = Point(3.4, 5.0)
        val disp = Vector.make(2.3, 4.5)

        assertEquals(
            point.displaced(disp),
            AffineTransform.makeTranslation(disp.u, disp.v).apply(point)
        )
    }

    @Test
    fun `scale point`() {
        val point = Point(3.4, 5.0)
        val sx = 3.0
        val sy = 4.0

        assertEquals(
            Point(point.x * sx, point.y * sy),
            AffineTransform.makeScaling(sx, sy).apply(point)
        )
    }

    @Test
    fun `rotate point positive angle`() {
        val point = Point(5.0, 0.0)
        val angleInRadians = 0.5 * Math.PI

        assertEquals(
            Point(0.0, 5.0),
            AffineTransform.makeRotation(angleInRadians).apply(point)
        )
    }

    @Test
    fun `rotate point negative angle`() {
        val point = Point(5.0, 0.0)
        val angleInRadians = -0.5 * Math.PI

        assertEquals(
            Point(0.0, -5.0),
            AffineTransform.makeRotation(angleInRadians).apply(point)
        )
    }
}