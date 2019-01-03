package sola.angel.nums

import sola.angel.geom2d.TParam
import sola.angel.geom2d.diagram.TPosValue

fun interpolate(start: TPosValue<Double>, end: TPosValue<Double>, t: TParam): Double {
    val tFraction = (t.value - start.t.value) / (end.t.value - start.t.value)
    return start.value + tFraction * (end.value - start.value)
}

fun interpolateValues(startInclusive: Double, endInclusive: Double, steps: Int): List<Double> {
    val step = (endInclusive - startInclusive) / steps.toDouble()
    return IntRange(0, steps).map { index -> startInclusive + index * step }
}