package sola.angel.geom2d

class OpenRange(val start: Double, val end: Double) {

    val length: Double = end - start

    fun contains(value: Double): Boolean = value > start && value < end

    fun overlaps(other: OpenRange): Boolean {
        return contains(other.start) || contains(other.end)
                || other.contains(start) || other.contains(end)
    }

    fun overlapRange(other: OpenRange): OpenRange? {
        if (!overlaps(other)) return null
        return OpenRange(
            Math.max(start, other.start),
            Math.min(end, other.end)
        )
    }
}