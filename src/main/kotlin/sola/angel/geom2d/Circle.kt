package sola.angel.geom2d

import sola.angel.geom2d.contracts.PointContainable
import sola.angel.nums.fuzzyEquals

/**
 * Simple closed shape.
 * Set of all points in a plane that are at a given distance from a given point, the center.
 */
class Circle(val center: Point, val radius: Double) : PointContainable {

    /* METHODS */
    override fun containsPoint(p: Point): Boolean {
        return p.distanceTo(center) < radius
    }

    /* EQUALS */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other is Circle) {
            return center.equals(other.center)
                    && fuzzyEquals(radius, other.radius)
        }

        return false
    }

    override fun hashCode(): Int {
        var result = center.hashCode()
        result = 31 * result + radius.hashCode()
        return result
    }
}