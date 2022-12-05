fun main() {
    fun part1(input: List<String>): Int = input.map { rucksack ->
        rucksack.substring(0 until rucksack.length / 2).toCharArray() to
                rucksack.substring(rucksack.length / 2).toCharArray()
    }.flatMap { (first, second) -> first intersect second.toSet() }.map { item -> item.toScore() }.sum()

    fun part2(input: List<String>): Int = input
        .chunked(3)
        .map { group ->
            group.zipWithNext()
                .map { (first, second) ->
                    first.toSet() intersect second.toSet()
                }
        }.flatMap { sharedItems ->
            sharedItems[0] intersect sharedItems[1]
        }.sumOf { it.toScore() }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)


    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

private fun Char.toScore(): Int = if (this.isUpperCase()) {
    this.code - 38
} else {
    this.code - 96
}