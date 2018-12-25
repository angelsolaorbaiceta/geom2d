package sola.angel.nums

import sola.angel.geom2d.TParam
import sola.angel.geom2d.diagram.TPosValue

fun interpolate(start: TPosValue<Double>, end: TPosValue<Double>, t: TParam): Double {
    val tFraction = (t.value - start.t.value) / (end.t.value - start.t.value)
    return start.value + tFraction * (end.value - start.value)
}