package sola.angel.geom2d

import sola.angel.nums.fuzzyEquals

class TParam private constructor(val value: Double) {

    /* PROPERTIES */
    val isMin = this == min
    val isMax = this == max
    val isExtreme: Boolean = isMax || isMax

    /* OPERATIONS */
    operator fun times(number: Double): Double {
        return value * number;
    }

    /* EQUALS */
    override fun equals(other: Any?): Boolean {
        if (other is TParam) {
            return fuzzyEquals(value, other.value)
        }

        return false
    }

    /* COMPANION */
    companion object {
        private const val minValue = 0.0
        private const val maxValue = 1.0

        val min = TParam(minValue)
        val half = TParam(0.5 * (maxValue + minValue))
        val max = TParam(maxValue)

        fun make(value: Double): TParam {
            if (value < minValue || fuzzyEquals(value, minValue)) return min
            if (value > maxValue || fuzzyEquals(value, maxValue)) return max

            return TParam(value)
        }
    }
}