package sola.angel.geom2d

/**
 * An Affine Transformation is function between affine spaces which preserves points, straight lines
 * and planes. Also, sets of parallel lines remain parallel after an affine transformation.
 *
 * An affine transformation does not necessarily preserve angles between lines or distances between
 * points, though it does preserve ratios of distances between points lying on a straight line.
 */
class AffineTransform(
    private val scaleX: Double,
    private val scaleY: Double,
    private val translationX: Double,
    private val translationY: Double,
    private val shearX: Double = 0.0,
    private val shearY: Double = 0.0
) {

    //#region METHODS : APPLY
    fun apply(point: Point): Point =
        Point(
            (scaleX * point.x) + (shearX * point.y) + translationX,
            (shearY * point.x) + (scaleY * point.y) + translationY
        )

    fun apply(segment: Segment): Segment =
        Segment(
            apply(segment.start),
            apply(segment.end)
        )

    fun apply(polygon: Polygon): Polygon =
        Polygon(
            polygon.vertices.map { apply(it) }
        )

    fun applyScale(size: Size): Size =
        Size(
            size.width * scaleX,
            size.height * scaleY
        )

    fun applyScaleAndDisplacement(rect: Rect): Rect =
        Rect(
            apply(rect.origin),
            applyScale(rect.size)
        )
    //#endregion

    //#region METHODS : CREATION
    /**
     * Returns a new Affine Transformation result of concatenating this one with a
     * displacement.
     */
    fun displaced(dx: Double, dy: Double): AffineTransform =
        AffineTransform(
            scaleX = scaleX,
            scaleY = scaleY,
            translationX = translationX + dx,
            translationY = translationY + dy,
            shearX = shearX,
            shearY = shearY
        )

    /**
     * Returns a new Affine Transformation result of concatenating this one with a
     * scaling operation about a given center point.
     */
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
    //#endregion

    //#region COMPANION OBJECT
    companion object {
        val identity = AffineTransform(1.0, 1.0, 0.0, 0.0)
        val invertedY = AffineTransform(1.0, -1.0, 0.0, 0.0)

        /**
         * Creates a new Affine Transformation which moves or translates primitives a
         * given displacement vector (dx, dy).
         */
        fun makeTranslation(dx: Double, dy: Double): AffineTransform =
            AffineTransform(1.0, 1.0, dx, dy)

        /**
         * Creates a new Affine Transformation which scales primitives a given amount (sx, xy)
         * about a given center point.
         */
        fun makeScaling(sx: Double, sy: Double, center: Point = Point.origin): AffineTransform =
            AffineTransform(
                scaleX = sx,
                scaleY = sy,
                translationX = center.x * (1.0 - sx),
                translationY = center.y * (1.0 - sy)
            )

        /**
         * Creates a new Affine Transformation which rotates primitives a given angle in radians
         * about a given center point.
         */
        fun makeRotation(radians: Double, center: Point = Point.origin): AffineTransform {
            val cos = Math.cos(radians)
            val sin = Math.sin(radians)
            val oneMinusCos = 1.0 - cos

            return AffineTransform(
                scaleX = cos,
                scaleY = cos,
                translationX = center.x * oneMinusCos + center.y * sin,
                translationY = center.y * oneMinusCos - center.x * sin,
                shearX = -sin,
                shearY = sin
            )
        }
    }
    //#endregion
}