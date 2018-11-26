package sola.angel.geom2d

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VectorOperationTest {

    private val u = Vector.make(3.0, 5.0)
    private val v = Vector.make(2.0, 1.0)

    @Test
    fun `add vectors`() {
        assertEquals(
            Vector.make(5.0, 6.0),
            u.plus(v)
        )
    }

    @Test
    fun `subtract vectors`() {
        assertEquals(
            Vector.make(1.0, 4.0),
            u.minus(v)
        )
    }

    @Test
    fun `dot product of vectors`() {
        assertEquals(
            11.0,
            u.dotTimes(v)
        )
    }

    @Test
    fun `cross product of vectors`() {
        assertEquals(
            -7.0,
            u.crossTimes(v)
        )
    }
}