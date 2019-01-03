package sola.angel.geom2d

import sola.angel.nums.fuzzyEquals
import sola.angel.nums.interpolateValues

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
     * Returns a new [AffineTransform] result of concatenating this one with a displacement.
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
     * Returns a new [AffineTransform] result of concatenating this one with a
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

    /**
     * Returns a new [AffineTransform] result of concatenating this one with another.
     */
    fun concatenating(other: AffineTransform): AffineTransform =
        AffineTransform(
            scaleX = scaleX * other.scaleX + shearX * other.shearY,
            scaleY = scaleY * other.scaleY + shearY * other.shearX,
            translationX = translationX + scaleX * other.translationX + shearX * other.translationY,
            translationY = translationY + shearY * other.translationX + scaleY * other.translationY,
            shearX = scaleX * other.shearX + shearX * other.scaleY,
            shearY = shearY * other.scaleX + scaleY * other.shearY
        )

    /**
     * Returns a sequence of [AffineTransform]s which define an animation from a given start
     * transformation to a target end transformation using as many steps as indicated.
     */
    fun interpolatingValuesTo(end: AffineTransform, steps: Int): List<AffineTransform> {
        val scalesX = interpolateValues(scaleX, end.scaleX, steps)
        val scalesY = interpolateValues(scaleY, end.scaleY, steps)
        val translationsX = interpolateValues(translationX, end.translationX, steps)
        val translationsY = interpolateValues(translationY, end.translationY, steps)
        val shearsX = interpolateValues(shearX, end.shearX, steps)
        val shearsY = interpolateValues(shearY, end.shearY, steps)

        return IntRange(0, steps)
            .map { i ->
                AffineTransform(
                    scaleX = scalesX[i],
                    scaleY = scalesY[i],
                    translationX = translationsX[i],
                    translationY = translationsY[i],
                    shearX = shearsX[i],
                    shearY = shearsY[i]
                )
            }
    }
    //#endregion

    //#region EQUALS, HASH & TO STRING
    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other is AffineTransform) {
            return fuzzyEquals(scaleX, other.scaleX) &&
                    fuzzyEquals(scaleY, other.scaleY) &&
                    fuzzyEquals(translationX, other.translationX) &&
                    fuzzyEquals(translationY, other.translationY) &&
                    fuzzyEquals(shearX, other.shearX) &&
                    fuzzyEquals(shearY, other.shearY)
        }

        return false
    }

    override fun hashCode(): Int {
        var result = scaleX.hashCode()
        result = 31 * result + scaleY.hashCode()
        result = 31 * result + translationX.hashCode()
        result = 31 * result + translationY.hashCode()
        result = 31 * result + shearX.hashCode()
        result = 31 * result + shearY.hashCode()
        return result
    }

    override fun toString(): String =
        "Affine: [ sx = $scaleX, sy = $scaleY, tx = $translationX, ty = $translationY, shx = $shearX, shy = $shearY ]"
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