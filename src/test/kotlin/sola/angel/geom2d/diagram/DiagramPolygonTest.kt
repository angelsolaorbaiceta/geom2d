package sola.angel.geom2d.diagram

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import sola.angel.geom2d.Point
import sola.angel.geom2d.Segment
import sola.angel.geom2d.TParam

class DiagramPolygonTest {

    private val segment = Segment(Point.origin, Point(10.0, 0.0))
    private val diagramPolygon = DiagramPolygon(
        segment,
        listOf(
            DiagramPolygon.Vertex(TParam.min, null, Point.origin),
            DiagramPolygon.Vertex(TParam.min, 5.0, Point(0.0, 2.5)),
            DiagramPolygon.Vertex(TParam.max, 10.0, Point(0.0, 5.0)),
            DiagramPolygon.Vertex(TParam.max, null, Point(10.0, 0.0))
        )
    )

    @Test
    fun `no closest vertex when point is outside bounds`() {
        assertNull(
            diagramPolygon.closestVertexTo(Point(5.0, 5.5), 2.0)
        )
    }

    @Test
    fun `no closest vertex when point is closer to non-value vertex`() {
        assertNull(
            diagramPolygon.closestVertexTo(Point(0.1, 0.1), 2.0)
        )
    }

    @Test
    fun `closest vertex to point`() {
        assertEquals(
            Point(0.0, 2.5),
            diagramPolygon.closestVertexTo(Point(0.1, 2.6), 2.0)!!.position
        )
    }
}