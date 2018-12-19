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

    @Test
    fun `can be scaled`() {
        assertEquals(
            Vector.make(4.0, 6.0),
            Vector.make(2.0, 3.0).scaled(2.0)
        )
    }

    @Test
    fun `compute opposite`() {
        assertEquals(
            Vector.make(-3.0, -4.0),
            Vector.make(3.0, 4.0).opposite()
        )
    }

    @Test
    fun `keep orientation with new length`() {
        assertEquals(
            Vector.make(7.0, 0.0),
            Vector.make(4.0, 0.0).withLength(7.0)
        )
    }

    @Test
    fun `compute perpendicular`() {
        assertEquals(
            Vector.make(-5.0, 3.0),
            Vector.make(3.0, 5.0).perpendicular()
        )
    }
}