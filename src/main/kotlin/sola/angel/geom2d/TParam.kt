package sola.angel.geom2d

import sola.angel.nums.fuzzyEquals

/**
 * Parameter used for iteration which value goes from a minimum (0.0 to a maximum (1.0).
 * Construction using factory ensures valid values which are always inside the range.
 */
class TParam private constructor(val value: Double) : Comparable<TParam> {

    /* PROPERTIES */
    val isMin = this == min
    val isMax = this == max
    val isExtreme: Boolean = isMax || isMax

    /* OPERATIONS */
    operator fun times(number: Double): Double {
        return value * number;
    }

    /* METHODS */
    override fun compareTo(other: TParam): Int {
        return when {
            fuzzyEquals(value, other.value) -> return 0
            value < other.value -> -1
            else -> 1
        }
    }

    /* EQUALS, HASH & TO STRING */
    override fun equals(other: Any?): Boolean {
        if (other is TParam) {
            return fuzzyEquals(value, other.value)
        }

        return false
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String = "T: $value"

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