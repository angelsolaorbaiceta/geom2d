package sola.angel.geom2d

import kotlin.math.max
import kotlin.math.min

fun findExtremes(points: Iterable<Point>): Pair<Point, Point> {
    if (points.count() < 1) {
        throw Exception("Cannot find extremes from less than one point")
    }

    val firstPoint = points.first()
    var minX = firstPoint.x
    var maxX = firstPoint.x
    var minY = firstPoint.y
    var maxY = firstPoint.y

    points.forEach { p ->
        minX = min(minX, p.x)
        maxX = max(maxX, p.x)
        minY = min(minY, p.y)
        maxY = max(maxY, p.y)
    }

    return Pair(
        Point(minX, minY),
        Point(maxX, maxY)
    )
}