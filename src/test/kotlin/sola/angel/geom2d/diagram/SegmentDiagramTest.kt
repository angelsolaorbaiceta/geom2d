package sola.angel.geom2d.diagram

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import sola.angel.geom2d.Point
import sola.angel.geom2d.Segment
import sola.angel.geom2d.TParam

class SegmentDiagramTest {

    private val segment = Segment(Point.origin, Point(10.0, 10.0))
    private val triangleValues = listOf(
        TPosValue(TParam.min, 10.0),
        TPosValue(TParam.half, 30.0),
        TPosValue(TParam.max, 10.0)
    )

    @Test
    fun `values are converted to ordered list`() {
        val values = listOf(
            TPosValue(TParam.max, 1.0),
            TPosValue(TParam.min, 4.0)
        )
        val diagram = SegmentDiagram(segment, values)

        assertEquals(
            values.sorted(),
            diagram.sortedValues
        )
    }

    @Test
    fun `value is zero for non defined stretch`() {
        val values = listOf(
            TPosValue(TParam.half, 1.0),
            TPosValue(TParam.max, 4.0)
        )
        val diagram = SegmentDiagram(segment, values)

        assertEquals(
            0.0,
            diagram.valueAt(TParam.make(0.3))
        )
    }

    @Test
    fun `get value at existing position`() {
        val diagram = SegmentDiagram(segment, triangleValues)

        assertEquals(
            30.0,
            diagram.valueAt(TParam.half)
        )
    }

    @Test
    fun `get value at middle position`() {
        val diagram = SegmentDiagram(segment, triangleValues)

        assertEquals(
            20.0,
            diagram.valueAt(TParam.make(0.25))
        )
    }

    @Test
    fun `make with constant value`() {
        val diagram = SegmentDiagram.makeWithConstantValue(segment, 20.0)

        assertEquals(
            listOf(TPosValue(TParam.min, 20.0), TPosValue(TParam.max, 20.0)),
            diagram.sortedValues
        )
    }

    @Test
    fun `make with extreme values`() {
        val diagram = SegmentDiagram.makeWithExtremeValues(segment, 20.0, 30.0)

        assertEquals(
            listOf(TPosValue(TParam.min, 20.0), TPosValue(TParam.max, 30.0)),
            diagram.sortedValues
        )
    }
}