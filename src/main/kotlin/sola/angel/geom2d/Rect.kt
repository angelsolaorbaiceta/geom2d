package sola.angel.geom2d

import sola.angel.geom2d.TParam.Companion.min
import sola.angel.geom2d.contracts.PointContainable

class Rect(val origin: Point, val size: Size) : PointContainable {

    /* PROPERTIES */
    val left = origin.x
    val right = origin.x + size.width
    val bottom = origin.y
    val top = origin.y + size.height

    val center = origin.displaced(size.asVector(), 0.5)

    //#region METHODS
    fun asPolygon(): Polygon =
        Polygon(
            listOf(
                origin,
                Point(right, bottom),
                Point(right, top),
                Point(left, top)
            )
        )

    override fun containsPoint(p: Point): Boolean {
        return p.x > left
                && p.x < right
                && p.y > bottom
                && p.y < top
    }

    fun intersectionWith(other: Rect): Rect? {
        val hOverlap = horizontalOverlapWith(other) ?: return null
        val vOverlap = verticalOverlapWith(other) ?: return null
        return Rect(
            Point(hOverlap.start, vOverlap.start),
            Size(hOverlap.length, vOverlap.length)
        )
    }

    private fun horizontalOverlapWith(other: Rect): OpenRange? {
        val thisRange = OpenRange(left, right)
        val otherRange = OpenRange(other.left, other.right)

        return thisRange.overlapRange(otherRange)
    }

    private fun verticalOverlapWith(other: Rect): OpenRange? {
        val thisRange = OpenRange(bottom, top)
        val otherRange = OpenRange(other.bottom, other.top)

        return thisRange.overlapRange(otherRange)
    }

    fun includingPoints(points: Iterable<Point>, margin: Double = 0.0): Rect {
        val outsidePoints = points.filter { !containsPoint(it) }
        if (outsidePoints.isEmpty()) return this

        val (min, max) = findExtremes(outsidePoints)
        val origin = Point(
            if (min.x < left) min.x - margin else left,
            if (min.y < bottom) min.y - margin else bottom
        )
        val corner = Point(
            if (max.x > right) max.x + margin else right,
            if (max.y > top) max.y + margin else top
        )

        return Rect(origin, (corner - origin).toSize())
    }
    //#endregion

    //#region EQUALS, HASH & TO STRING
    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other is Rect) {
            return origin.equals(other.origin) && size.equals(other.size)
        }

        return false
    }

    override fun hashCode(): Int {
        var result = origin.hashCode()
        result = 31 * result + size.hashCode()
        return result
    }

    override fun toString(): String = "origin: $origin, size: $size"

    //#endregion

    //#region COMPANION
    companion object {
        val nilRect = Rect(Point.origin, Size.zero)

        fun makeContaining(points: Iterable<Point>, margin: Double = 0.0): Rect {
            val (min, max) = findExtremes(points)
            val size = max - min

            return Rect(
                Point(
                    min.x - margin,
                    min.y - margin
                ),
                Size(
                    size.x + 2.0 * margin,
                    size.y + 2.0 * margin
                )
            )
        }

        fun makeCentered(center: Point, size: Size): Rect =
            Rect(
                Point(center.x - size.width / 2.0, center.y - size.height / 2.0),
                size
            )
    }
    //#endregion
}