package sola.angel.geom2d

import sola.angel.nums.fuzzyEquals
import sola.angel.nums.isCloseToOne
import sola.angel.nums.isCloseToZero

/**
 * Geometric object that has magnitude and direction, and is represented
 * by its horizontal and vertical projections (u, v).
 */
class Vector private constructor(val u: Double, val v: Double) {

    //#region PROPERTIES
    val norm = Math.sqrt(u * u + v * v)
    val isNormal = isCloseToOne(norm)

    val cosine: Double by lazy {
        dotTimes(Vector.iVersor) / norm
    }
    val sine: Double by lazy {
        crossTimes(iVersor) / norm
    }
    //#endregion

    //#region METHODS
    fun normalized(): Vector =
        if (isNormal) this else Vector(u / norm, v / norm)

    fun opposite(): Vector = Vector(-u, -v)

    fun withLength(length: Double): Vector = normalized().scaled(length)

    fun scaled(scale: Double): Vector =
        when {
            isCloseToOne(scale) -> this
            isCloseToZero(scale) -> Vector.zero
            else -> Vector(scale * u, scale * v)
        }

    operator fun plus(addend: Vector): Vector =
        Vector(u + addend.u, v + addend.v)

    operator fun minus(subtrahend: Vector): Vector =
        Vector(u - subtrahend.u, v - subtrahend.v)

    fun dotTimes(multiplicand: Vector): Double =
        (u * multiplicand.u) + (v * multiplicand.v)

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

    fun perpendicular(): Vector = Vector(-v, u)
    //#endregion

    //#region EQUALS, HASH & TO STRING
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

    override fun toString(): String = "{$u, $v}"
    //#endregion

    //#region COMPANION
    companion object {
        val zero = Vector(0.0, 0.0)
        val iVersor = Vector(1.0, 0.0)
        val jVersor = Vector(0.0, 1.0)

        fun make(u: Double, v: Double): Vector = Vector(u, v)
        fun makeBetween(start: Point, end: Point): Vector = start.vectorTo(end)
        fun makeVersor(u: Double, v: Double): Vector = Vector(u, v).normalized()
        fun makeVersorBetween(start: Point, end: Point): Vector = start.versorTo(end)
    }
    //#endregion
}