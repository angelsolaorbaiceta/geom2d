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

    fun intersectionWith(other: Rect): Rect? {
        val hOverlap = horizontalOverlapWith(other) ?: return null
        val vOverlap = verticalOverlapWith(other) ?: return null
        return Rect(
            Point(hOverlap.start, vOverlap.start),
            Size(hOverlap.length, vOverlap.length)
        )
    }

    private fun horizontalOverlapWith(other: Rect): OpenRange? {
        val thisRange = OpenRange(left, right)
        val otherRange = OpenRange(other.left, other.right)

        return thisRange.overlapRange(otherRange)
    }

    private fun verticalOverlapWith(other: Rect): OpenRange? {
        val thisRange = OpenRange(bottom, top)
        val otherRange = OpenRange(other.bottom, other.top)

        return thisRange.overlapRange(otherRange)
    }

    /* COMPANION */
    companion object {
        val nilRect = Rect(Point.origin, Size.zero)

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