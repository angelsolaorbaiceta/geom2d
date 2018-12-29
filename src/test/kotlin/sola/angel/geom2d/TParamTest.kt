package sola.angel.geom2d

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import sola.angel.geom2d.TParam

class TParamTest {

    @Test
    fun `subdivide in parts`() {
        val tValues = TParam.subdivideInParts(4)

        assertEquals(
            listOf(TParam.min, TParam.make(0.25), TParam.half, TParam.make(0.75), TParam.max),
            tValues
        )
    }
}