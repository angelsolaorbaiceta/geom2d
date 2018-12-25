package sola.angel.geom2d.contracts

import sola.angel.geom2d.Point

/**
 * Contract implemented by primitives which can check whether they are close enough to a given point,
 * and which compute their closest point to it.
 */
interface ProximityCheckable {
    fun closestPointTo(point: Point): Point
    fun isCloseEnoughTo(point: Point, tolerance: Double): Boolean
}