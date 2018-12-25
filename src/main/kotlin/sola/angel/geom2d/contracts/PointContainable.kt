package sola.angel.geom2d.contracts

import sola.angel.geom2d.Point

/**
 * Contract implemented by primitives which have can check if they contain a point.
 */
interface PointContainable {
    fun containsPoint(p: Point): Boolean
}