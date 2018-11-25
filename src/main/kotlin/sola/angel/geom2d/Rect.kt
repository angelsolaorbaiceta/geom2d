package sola.angel.geom2d

import sola.angel.geom2d.contracts.PointContainable

class Rect(val origin: Point, val size: Size) : PointContainable {

    /* PROPERTIES */
    val left = origin.x
    val right = origin.x + size.width
    val bottom = origin.y
    val top = origin.y + size.height

    /* METHODS */
    override fun containsPoint(p: Point): Boolean {
        return p.x > left
                && p.x < right
                && p.y > bottom
                && p.y < top
    }

    /* EQUALS */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other is Rect) {
            return origin.equals(other.origin) && size.equals(other.size)
        }

        return false
    }

    override fun hashCode(): Int {
        var result = origin.hashCode()
        result = 31 * result + size.hashCode()
        return result
    }

    /* COMPANION */
    companion object {
        fun makeContaining(points: Iterable<Point>, margin: Double = 0.0): Rect {
            val orderedXCoords = points.map { it.x }.sorted()
            val orderedYCoords = points.map { it.y }.sorted()

            return Rect(
                Point(
                    orderedXCoords.first() - margin,
                    orderedYCoords.first() - margin
                ),
                Size(
                    (orderedXCoords.last() - orderedXCoords.first()) + 2.0 * margin,
                    (orderedYCoords.last() - orderedYCoords.first()) + 2.0 * margin
                )
            )
        }
    }
}