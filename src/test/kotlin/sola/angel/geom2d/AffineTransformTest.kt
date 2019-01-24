package sola.angel.geom2d

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.PI

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
    fun `scale point about center`() {
        val point = Point(4.0, 4.0)
        val center = Point(2.0, 2.0)

        assertEquals(
            Point(8.0, 10.0),
            AffineTransform.makeScaling(3.0, 4.0, center).apply(point)
        )
    }

    @Test
    fun `transform rect with positive scale`() {
        val rect = Rect(Point.origin, Size.make(10.0, 20.0))
        val transform = AffineTransform(2.0, 3.0, 5.0, 7.0)

        assertEquals(
            Rect(Point(5.0, 7.0), Size.make(20.0, 60.0)),
            transform.applyScaleAndDisplacement(rect)
        )
    }

    @Test
    fun `transform rect with negative scale`() {
        val rect = Rect(Point.origin, Size.make(10.0, 20.0))
        val transform = AffineTransform(2.0, -3.0, 5.0, 7.0)

        assertEquals(
            Rect(Point(5.0, -53.0), Size.make(20.0, 60.0)),
            transform.applyScaleAndDisplacement(rect)
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

    @Test
    fun `scale size`() {
        val size = Size.make(3.0, 5.0)
        val scale = AffineTransform.makeScaling(2.0, 4.0)

        assertEquals(
            Size.make(6.0, 20.0),
            scale.applyScale(size)
        )
    }

    @Test
    fun `rotate angle about a point`() {
        val center = Point(2.0, 2.0)
        val point = Point(4.0, 4.0)
        val transform = AffineTransform.makeRotation(PI / 4.0, center)

        assertEquals(
            center + Point(0.0, 2.0 * Math.sqrt(2.0)),
            transform.apply(point)
        )
    }

    @Test
    fun `concatenate two Affine Transformations`() {
        val tA = AffineTransform(1.0, 5.0, 3.0, 6.0, 2.0, 4.0)
        val tB = AffineTransform(6.0, 2.0, 4.0, 1.0, 5.0, 3.0)

        assertEquals(
            AffineTransform(12.0, 30.0, 9.0, 27.0, 9.0, 39.0),
            tA.concatenating(tB)
        )
    }

    @Test
    fun `inverse transformation`() {
        val transf = AffineTransform(2.0, 4.0, 3.0, 5.0, 8.0, 10.0)
        val point = Point(2.0, 5.0)

        assertEquals(
            point,
            transf.inverse.apply(
                transf.apply(point)
            )
        )
    }
}