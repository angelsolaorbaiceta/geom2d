package sola.angel.geom2d.contracts

import sola.angel.geom2d.Point

interface PointContainable {
    fun containsPoint(p: Point): Boolean
}