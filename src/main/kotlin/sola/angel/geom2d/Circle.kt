package sola.angel.geom2d

import sola.angel.geom2d.contracts.PointContainable

class Circle(val center: Point, val radius: Double): PointContainable {

    /* METHODS */
    override fun containsPoint(p: Point): Boolean {
        return p.distanceTo(center) < radius
    }
}