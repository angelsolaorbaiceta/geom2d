package sola.angel.geom2d

import sola.angel.geom2d.contracts.PointContainable
import sola.angel.nums.TWO_PI
import sola.angel.nums.fuzzyEquals
import sola.angel.utils.makeRoundPairs

/**
 * Plane figure that is described by a finite number of straight line segments connected
 * to form a polygonal chain or polygonal circuit
 */
class Polygon(val vertices: List<Point>) : PointContainable {

    //#region PROPERTIES
    val sides: List<Segment> by lazy {
        makeRoundPairs(vertices).map { pair -> Segment(pair.first, pair.second) }
    }
    //#endregion

    //#region METHODS
    fun geometricalCenter(): Point =
        Point(
            vertices.map { it.x }.average(),
            vertices.map { it.y }.average()
        )

    fun rotatedAboutPoint(radians: Double, center: Point): Polygon {
        val rotation = AffineTransform.makeRotation(radians, center)
        return rotation.apply(this)
    }

    fun rotatedAboutCenter(radians: Double): Polygon = rotatedAboutPoint(radians, geometricalCenter())

    override fun containsPoint(p: Point): Boolean {
        val vectors = vertices.map { vertex -> Vector.makeBetween(p, vertex) }
        val angle = makeRoundPairs(vectors).map { pair -> pair.first.angleTo(pair.second) }.sum()

        return fuzzyEquals(angle, TWO_PI)
    }

    //#endregion

    //#region EQUALS, HASH & TO STRING
    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other is Polygon) {
            return vertices == other.vertices
        }

        return false
    }

    override fun hashCode(): Int {
        return vertices.hashCode()
    }

    override fun toString(): String {
        return vertices.toString()
    }

    //#endregion
}
