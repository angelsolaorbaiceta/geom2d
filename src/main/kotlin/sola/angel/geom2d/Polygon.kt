package sola.angel.geom2d

class Polygon(val vertices: List<Point>) {

    //#region METHODS
    fun geometricalCenter(): Point =
        Point(
            vertices.map { it.x }.average(),
            vertices.map { it.y }.average()
        )

    fun rotateAboutPoint(radians: Double, center: Point): Polygon {
        val rotation = AffineTransform.makeRotation(radians, center)
        return rotation.apply(this)
    }

    fun rotateAboutCenter(radians: Double): Polygon = rotateAboutPoint(radians, geometricalCenter())
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
