package sola.angel.geom2d

import sola.angel.nums.fuzzyEquals

class Size(val width: Double, val height: Double) {

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

    companion object {
        val zero = Size(0.0, 0.0)
    }
}