package sola.angel.geom2d

import sola.angel.nums.fuzzyEquals
import kotlin.math.abs

class Size private constructor(val width: Double, val height: Double) {

    val area = width * height

    fun asVector(): Vector = Vector.make(width, height)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other is Size) {
            return fuzzyEquals(width, other.width)
                    && fuzzyEquals(height, other.height)
        }

        return false
    }

    override fun hashCode(): Int {
        var result = width.hashCode()
        result = 31 * result + height.hashCode()
        return result
    }

    override fun toString(): String = "width: $width, height: $height"

    companion object {
        val zero = Size(0.0, 0.0)

        fun make(width: Double, height: Double): Size = Size(abs(width), abs(height))
    }
}