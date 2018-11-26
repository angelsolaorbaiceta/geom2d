package sola.angel.geom2d

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class VectorTest {

    @Test
    fun `has norm`() {
        val vector = Vector.make(4.0, 3.0)
        assertEquals(
            vector.norm,
            5.0
        )
    }

    @Test
    fun `is normal if norm is one`() {
        val vector = Vector.makeVersor(3.0, 4.0)
        assertTrue(
            vector.isNormal
        )
    }

    @Test
    fun `is not normal if norm is not one`() {
        val vector = Vector.make(3.0, 4.0)
        assertFalse(
            vector.isNormal
        )
    }

    @Test
    fun `can be normalized`() {
        val vector = Vector.make(3.0, 4.0)
        val expected = Vector.make(3.0 / 5.0, 4.0 / 5.0)
        assertEquals(
            expected,
            vector.normalized()
        )
    }
}