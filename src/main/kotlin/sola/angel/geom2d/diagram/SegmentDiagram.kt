package sola.angel.geom2d.diagram

import sola.angel.geom2d.Segment
import sola.angel.geom2d.TParam
import sola.angel.nums.interpolate

/**
 * Diagram of Double values associated to positions given as [TParam] for a given [Segment].
 * The diagram x axis is the segment itself.
 */
class SegmentDiagram(
    val segment: Segment,
    values: Iterable<TPosValue<Double>>
) {

    /* PROPERTIES */
    val sortedValues = values.sorted()

    private val startT = sortedValues.first().t
    private val endT = sortedValues.last().t

    /* METHODS */
    fun valueAt(t: TParam): Double {
        if (t < startT || t > endT) return 0.0

        val foundPosValue = sortedValues.find { it.t == t }
        if (foundPosValue != null) return foundPosValue.value

        val smaller = sortedValues.last { it.t < t }
        val greater = sortedValues.first { it.t > t }
        return interpolate(smaller, greater, t)
    }

    /* COMPANION */
    companion object {
        fun makeWithConstantValue(segment: Segment, value: Double): SegmentDiagram =
            SegmentDiagram(
                segment,
                listOf(TPosValue(TParam.min, value), TPosValue(TParam.max, value))
            )

        fun makeWithExtremeValues(segment: Segment, startValue: Double, endValue: Double): SegmentDiagram =
            SegmentDiagram(
                segment,
                listOf(TPosValue(TParam.min, startValue), TPosValue(TParam.max, endValue))
            )
    }
}