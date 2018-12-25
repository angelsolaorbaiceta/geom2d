package sola.angel.geom2d.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import sola.angel.utils.makeRoundPairs

class CollectionsTest {

    @Test()
    fun `cannot create round pairs from empty collection`() {
        assertThrows<Exception> {
            makeRoundPairs(emptyList<Int>())
        }
    }

    @Test()
    fun `round pairs from collection with just one element is empty`() {
        assertTrue(
            makeRoundPairs(listOf(1)).isEmpty()
        )
    }

    @Test
    fun `round pairs of integer sequence`() {
        assertEquals(
            listOf(Pair(1, 2), Pair(2, 3), Pair(3, 1)),
            makeRoundPairs(listOf(1, 2, 3))
        )
    }
}