package sola.angel.geom2d

import sola.angel.geom2d.contracts.ProximityCheckable
import sola.angel.nums.isCloseToZero
import kotlin.math.abs

/**
 * Part of a line that is bounded by two distinct end points, and contains every point on
 * the line between its endpoints.
 */
class Segment(val start: Point, val end: Point) : ProximityCheckable {

    //#region PROPERTIES
    val middle: Point = pointAt(TParam.half)
    val directorVersor: Vector = Vector.makeVersorBetween(start, end)
    val normalVersor: Vector = directorVersor.perpendicular()

    val length: Double by lazy {
        start.distanceTo(end)
    }
    val height: Double by lazy {
        abs(end.y - start.y)
    }
    val width: Double by lazy {
        abs(end.x - start.x)
    }

    val bounds: Circle by lazy {
        Circle(middle, start.distanceTo(middle))
    }

    val isHorizontal: Boolean get() = isCloseToZero(height)
    val isVertical: Boolean get() = isCloseToZero(width)
    //#endregion

    //#region METHODS
    fun pointAt(t: TParam): Point = start.interpolate(end, t)

    fun length(fromT: TParam, toT: TParam): Double {
        val from = pointAt(fromT)
        val to = pointAt(toT)
        return from.distanceTo(to)
    }

    fun displaced(vector: Vector, times: Double = 1.0): Segment =
        Segment(
            start.displaced(vector, times),
            end.displaced(vector, times)
        )

    fun inverted(): Segment = Segment(this.end, this.start)

    override fun closestPointTo(point: Point): Point {
        val v = Vector.makeBetween(start, point)
        val timesDirVersor = v.dotTimes(directorVersor)
        val timesDirVersorOverLength = timesDirVersor / length

        if (timesDirVersorOverLength < 0.0) return start
        if (timesDirVersorOverLength > 1.0) return end

        return start.displaced(directorVersor, timesDirVersor)
    }

    override fun isCloseEnoughTo(point: Point, tolerance: Double): Boolean =
        if (!bounds.containsPoint(point)) false
        else closestPointTo(point).distanceTo(point) < tolerance

    //#endregion

    //#region EQUALS, HASH CODE & TO STRING
    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other is Segment) {
            return start == other.start && end == other.end
        }

        return false
    }

    override fun hashCode(): Int {
        var result = start.hashCode()
        result = 31 * result + end.hashCode()
        return result
    }

    override fun toString(): String = "from $start to $end"
    //#endregion
}