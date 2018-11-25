package sola.angel.geom2d

import sola.angel.nums.fuzzyEquals
import sola.angel.nums.isCloseToOne

class Vector private constructor(val u: Double, val v: Double) {

    /* PROPERTIES */
    val norm = Math.sqrt(u * u + v * v)
    val isNormal = isCloseToOne(norm)

    /* METHODS */
    fun normalized(): Vector =
        if (isNormal) this else Vector(u / norm, v / norm)

    fun scaled(scale: Double): Vector =
        if (isCloseToOne(scale)) this else Vector(scale * u, scale * v)

    operator fun plus(addend: Vector): Vector =
        Vector(u + addend.u, v + addend.v)

    operator fun minus(subtrahend: Vector): Vector =
        Vector(u - subtrahend.u, v - subtrahend.v)

    fun dotTimes(multiplicand: Vector): Double =
        (u * multiplicand.u) + (v + multiplicand.v)

    fun crossTimes(multiplicand: Vector): Double =
        (u * multiplicand.v) - (v * multiplicand.u)

    fun projectedOnto(other: Vector): Vector {
        val projectionValue = this.dotTimes(other)
        return other.normalized().scaled(projectionValue)
    }

    fun angleValueTo(other: Vector): Double {
        val acos = this.dotTimes(other) / (this.norm * other.norm)
        return Math.acos(acos)
    }

    fun angleTo(other: Vector): Double {
        val value = angleValueTo(other)
        val crossProduct = this.crossTimes(other)
        return Math.copySign(value, crossProduct)
    }

    /* EQUALS & HASH */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other is Vector) {
            return fuzzyEquals(u, other.u) && fuzzyEquals(v, other.v)
        }

        return false
    }

    override fun hashCode(): Int {
        var result = u.hashCode()
        result = 31 * result + v.hashCode()
        return result
    }

    /* COMPANION */
    companion object {
        val zero = Vector(0.0, 0.0)
        val iVersor = Vector(1.0, 0.0)
        val jVersor = Vector(0.0, 1.0)

        fun make(u: Double, v: Double): Vector = Vector(u, v)

        fun makeBetween(start: Point, end: Point): Vector {
            val vector = end - start
            return make(vector.x, vector.y)
        }

        fun makeVersor(u: Double, v: Double): Vector = Vector(u, v).normalized()

        fun makeVersorBetween(start: Point, end: Point): Vector {
            val vector = end - start
            return makeVersor(vector.x, vector.y)
        }
    }
}