package sola.angel.geom2d

import sola.angel.nums.isCloseToZero
import kotlin.text.Typography.times

class Segment(val start: Point, val end: Point) {

    /* PROPERTIES */
    val middle: Point
        get() = pointAt(TParam.half)

    val length: Double
        get() = start.distanceTo(end)

    val height: Double
        get() = Math.abs(end.y - start.y)

    val width: Double
        get() = Math.abs(end.x - start.x)

    val directorVersor: Vector = Vector.makeVersorBetween(start, end)
    val normalVersor: Vector = directorVersor.perpendicular()

    val bounds: Circle
        get() = Circle(middle, start.distanceTo(middle))

    val isHorizontal: Boolean
        get() = isCloseToZero(height)

    val isVertical: Boolean
        get() = isCloseToZero(width)

    /* METHODS */
    fun pointAt(t: TParam): Point = start.interpolate(end, t)

    fun length(fromT: TParam, toT: TParam): Double {
        val from = pointAt(fromT)
        val to = pointAt(toT)
        return from.distanceTo(to)
    }

    fun displaced(disp: Vector, times: Double = 1.0): Segment =
        Segment(
            start.displaced(disp, times),
            end.displaced(disp, times)
        )

    fun inverted(): Segment = Segment(this.end, this.start)

    fun closestPointTo(point: Point): Point {
        val v = Vector.makeBetween(start, point)
        val timesDirVersor = v.dotTimes(directorVersor)
        val timesDirVersorOverLength = timesDirVersor / length

        if (timesDirVersorOverLength < 0.0) return start
        if (timesDirVersorOverLength > 1.0) return end

        return start.displaced(directorVersor, timesDirVersor)
    }

    /* EQUALS */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other is Segment) {
            return start.equals(other.start) && end.equals(other.end)
        }

        return false
    }
}