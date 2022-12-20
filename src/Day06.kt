fun main() {
    fun CharSequence.isUnique() = toSet().count() == length

    fun solve(input: String, windowSize: Int): Int  {
        return input
            .windowedSequence(windowSize) {
                it.isUnique()
            }.indexOf(true) + windowSize
    }

    val testInput = readInput("Day06_test")
    check(solve(testInput[0], 4) == 7)
    check(solve(testInput[1], 4) == 5)
    check(solve(testInput[2], 4) == 6)
    check(solve(testInput[3], 4) == 10)
    check(solve(testInput[4], 4) == 11)

    check(solve(testInput[0], 14) == 19)
    check(solve(testInput[1], 14) == 23)
    check(solve(testInput[2], 14) == 23)
    check(solve(testInput[3], 14) == 29)
    check(solve(testInput[4], 14) == 26)

    val input = readInput("Day06")
    println(solve(input[0], 4))
    println(solve(input[0], 14))
}
