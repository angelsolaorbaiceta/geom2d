package sola.angel.utils

/**
 * Creates a new list from the given iterable by combining contiguous elements together and
 * including a pair of the last and first elements.
 *
 * Given [A, B, C], this function would yield: [<A, B>, <B, C>, <C, A>]
 */
internal fun <T> makeRoundPairs(sequence: Iterable<T>): List<Pair<T, T>> {
    val seqAsList = sequence.toList()

    if (seqAsList.isEmpty()) throw Exception("Cannot create round pairs from empty collection")
    if (seqAsList.size == 1) return emptyList()

    return IntRange(1, seqAsList.size)
        .map {index ->
            Pair(seqAsList[index - 1], seqAsList[index % seqAsList.size])
        }
        .toList()
}