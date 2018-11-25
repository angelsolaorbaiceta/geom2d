package sola.angel.nums

fun fuzzyEquals(a: Double, b: Double, epsilon: Double = 1e-10): Boolean = Math.abs(a - b) < epsilon

fun isCloseToZero(a: Double, epsilon: Double = 1e-10): Boolean = fuzzyEquals(a, 0.0, epsilon)

fun isCloseToOne(a: Double, epsilon: Double = 1e-10): Boolean = fuzzyEquals(a, 1.0, epsilon)