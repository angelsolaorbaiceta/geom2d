package sola.angel.geom2d

import sola.angel.nums.fuzzyEquals

class Size(val width: Double, val height: Double) {

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
    }
}