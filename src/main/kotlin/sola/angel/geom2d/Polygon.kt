package sola.angel.geom2d

class Polygon(val vertices: List<Point>) {

    fun geometricalCenter(): Point =
        Point(
            vertices.map { it.x }.average(),
            vertices.map { it.y }.average()
        )

    fun rotateAboutCenter(radians: Double): Polygon {
        val rotation = AffineTransform.makeRotation(radians, geometricalCenter())
        return rotation.apply(this)
    }
}
