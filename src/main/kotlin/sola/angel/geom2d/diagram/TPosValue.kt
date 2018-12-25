package sola.angel.geom2d.diagram

import sola.angel.geom2d.TParam

/**
 * Value of type T associated to a position given by [TParam].
 * Respects same ordering as [TParam].
 */
class TPosValue<T>(val t: TParam, val value: T) : Comparable<TPosValue<T>> {

    /* METHODS */
    override fun compareTo(other: TPosValue<T>): Int = t.compareTo(other.t)

    /* EQUALS, HASH & TO STRING */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other is TPosValue<*>) {
            return t == other.t && value == other.value
        }

        return false
    }

    override fun hashCode(): Int {
        var result = t.hashCode()
        result = 31 * result + (value?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String = "$t -> $value"
}