package sola.angel.geom2d

class AffineTransform(
    private val scaleX: Double,
    private val scaleY: Double,
    private val translationX: Double,
    private val translationY: Double,
    private val shearX: Double,
    private val shearY: Double
) {

    /* METHODS */
    fun apply(point: Point): Point {
        return Point(
            (scaleX * point.x) + (shearX * point.y) + translationX,
            (shearY * point.x) + (scaleY * point.y) + translationY
        )
    }

    /* CREATION */
    fun displaced(dx: Double, dy: Double): AffineTransform {
        return AffineTransform(
            scaleX = scaleX,
            scaleY = scaleY,
            translationX = translationX + dx,
            translationY = translationY + dy,
            shearX = shearX,
            shearY = shearY
        )
    }

    fun scaled(factor: Double, center: Point = Point.origin): AffineTransform {
        val oneMinusFactor = 1.0 - factor
        return AffineTransform(
            scaleX = scaleX * factor,
            scaleY = scaleY * factor,
            translationX = center.x * scaleX * oneMinusFactor + translationX,
            translationY = center.y * scaleY * oneMinusFactor + translationY,
            shearX = shearX,
            shearY = shearY
        )
    }

    /* COMPANION OBJECT */
    companion object {
        val identity = AffineTransform(1.0, 1.0, 0.0, 0.0, 0.0, 0.0)

        fun makeTranslation(dx: Double, dy: Double): AffineTransform =
            AffineTransform(1.0, 1.0, dx, dy, 0.0, 0.0)

        fun makeScaling(sx: Double, sy: Double): AffineTransform =
            AffineTransform(sx, sy, 0.0, 0.0, 0.0, 0.0)

        fun makeRotation(radians: Double): AffineTransform {
            val cos = Math.cos(radians)
            val sin = Math.sin(radians)
            return AffineTransform(cos, cos, 0.0, 0.0, -sin, sin)
        }
    }
}