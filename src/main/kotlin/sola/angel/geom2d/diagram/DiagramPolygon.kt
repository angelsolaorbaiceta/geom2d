package sola.angel.geom2d.diagram

import sola.angel.geom2d.*
import sola.angel.geom2d.contracts.ProximityCheckable

/**
 * Representation of a [SegmentDiagram] as a polygon which has the value of the diagram
 * associated to each of the vertices.
 *
 * Vertices which are part of the X axis not as real values of the diagram have an
 * associated value of null.
 */
class DiagramPolygon(
    private val segment: Segment,
    private val vertices: List<Vertex>
) {

    val polygon: Polygon = Polygon(vertices.map { it.position })
    private val bounds = Rect.makeContaining(polygon.vertices)

    /**
     * Returns the closest value vertex to the given point, provided its distance
     * to it is less than the tolerance.
     */
    fun closestVertexTo(point: Point, tolerance: Double): Vertex? =
        if (!bounds.containsPoint(point)) null
        else vertices.find { vertex -> vertex.isValueVertex && vertex.isCloseEnoughTo(point, tolerance) }

    /* VERTEX CLASS */
    class Vertex(val t: TParam, val diagramValue: Double?, val position: Point) : ProximityCheckable {
        val isValueVertex: Boolean = diagramValue != null
        override fun closestPointTo(point: Point): Point = position
        override fun isCloseEnoughTo(point: Point, tolerance: Double): Boolean = position.distanceTo(point) < tolerance
    }
}