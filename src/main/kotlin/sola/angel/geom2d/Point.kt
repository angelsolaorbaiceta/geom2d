package sola.angel.geom2d

import sola.angel.nums.fuzzyEquals

class Point(val x: Double, val y: Double) {

    /* OPERATIONS */
    operator fun plus(addend: Point): Point = Point(x + addend.x, y + addend.y)

    operator fun minus(subtrahend: Point): Point = Point(x - subtrahend.x, y - subtrahend.y)

    /* METHODS */
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

    /* EQUALS & HASH */
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

    /* COMPANION */
    companion object {
        val origin = Point(0.0, 0.0)
    }
}
