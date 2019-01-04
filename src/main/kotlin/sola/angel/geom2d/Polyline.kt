package sola.angel.geom2d

import sola.angel.geom2d.contracts.ProximityCheckable
import sola.angel.utils.makePairs

/**
 * Connected series of segments defined by all its vertices.
 */
data class Polyline(
    val points: List<Point>
) : ProximityCheckable {

    /* PROPERTIES */
    val stretches: List<Segment> by lazy {
        makePairs(points).map { Segment(it.first, it.second) }
    }

    val bounds: Rect by lazy {
        Rect.makeContaining(points)
    }

    /* METHODS */
    override fun closestPointTo(point: Point): Point =
        stretches
            .map { it.closestPointTo(point) }
            .sortedBy { it.distanceTo(point) }
            .first()

    override fun isCloseEnoughTo(point: Point, tolerance: Double): Boolean =
        stretches.any { it.closestPointTo(point).distanceTo(point) < tolerance }

    /* TO STRING */
    override fun toString(): String = points.joinToString(" -> ") { it.toString() }
}