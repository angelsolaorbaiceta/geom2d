package sola.angel.geom2d

import sola.angel.nums.fuzzyEquals

/**
 * Ordered pair (x, y) of numbers representing a position in a two-dimensional plane.
 */
class Point(val x: Double, val y: Double) : Comparable<Point> {

    //#region OPERATIONS
    operator fun plus(addend: Point): Point = Point(x + addend.x, y + addend.y)

    operator fun minus(subtrahend: Point): Point = Point(x - subtrahend.x, y - subtrahend.y)
    //#endregion

    //#region METHODS
    fun distanceTo(other: Point): Double {
        val deltaX = other.x - this.x
        val deltaY = other.y - this.y
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY)
    }

    fun displaced(vector: Vector, times: Double = 1.0): Point {
        val displacement = vector.scaled(times)
        return Point(x + displacement.u, y + displacement.v)
    }

    fun interpolate(other: Point, t: TParam): Point {
        return Point(
            x + t * (other.x - x),
            y + t * (other.y - y)
        )
    }

    fun toSize(): Size = Size.make(x, y)
    fun toVector(): Vector = Vector.make(x, y)
    fun vectorTo(end: Point): Vector = Vector.make(end.x - x, end.y - y)
    fun versorTo(end: Point): Vector = Vector.makeVersor(end.x - x, end.y - y)

    override fun compareTo(other: Point): Int =
        when {
            this == other -> 0
            fuzzyEquals(x, other.x) -> y.compareTo(other.y)
            else -> x.compareTo(other.x)
        }
    //#endregion

    //#region EQUALS, HASH & TO STRING
    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other is Point) {
            return fuzzyEquals(x, other.x) && fuzzyEquals(y, other.y)
        }

        return false
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }

    override fun toString(): String = "($x, $y)"
    //#endregion

    /* COMPANION */
    companion object {
        val origin = Point(0.0, 0.0)
    }
}
