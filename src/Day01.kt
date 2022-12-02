fun main() {
    fun part1(input: List<String>): Int {
        var currentElve = 0
        var maxElve = 0

        for (line in input) {
            if (line.isBlank()) {
                if (currentElve > maxElve) {
                    maxElve = currentElve
                }
                currentElve = 0
            } else {
                currentElve += line.toInt()
            }
        }

        return maxElve
    }

    fun pushValue(value: Int, array: Array<Int>) {
        var current = value
        var overflow = 0
        for (i in 0..array.size - 1) {
            if (current > array[i]) {
                overflow = array[i]
                array[i] = current
                current = 0
            }
            if (overflow > array[i]) {
                val tmp = array[i]
                array[i] = overflow
                overflow = tmp
            }
        }
    }

    fun part2(input: List<String>): Int {
        var currentElve = 0
        val maxElves = arrayOf(0, 0, 0)

        for (line in input) {
            if (line.isNotBlank()) {
                currentElve += line.toInt()
            } else {
                pushValue(currentElve, maxElves)
                currentElve = 0
            }
        }
        if (currentElve > 0) {
            pushValue(currentElve, maxElves)
        }

        return maxElves.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
