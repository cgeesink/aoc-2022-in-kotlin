data class Command(val count: Int, val from: Int, val to: Int) {
    companion object {
        fun of(line: String): Command {
            return line.split(" ")
                .filterIndexed { index, _ -> index % 2 == 1 }
                .map { it.toInt() }
                .let { Command(it[0], it[1] - 1, it[2] - 1) }
        }
    }
}

class Day05 {
    fun numberOfStackes(lines: List<String>) =
        lines
            .dropWhile { it.contains("[") }
            .first()
            .split(" ")
            .filter { it.isNotBlank() }
            .maxOf { it.toInt() }

    fun populateStackes(lines: List<String>, onCharacterFound: (Int, Char) -> Unit) {
        lines.filter { it.contains("[") }
            .map { line ->
                line.mapIndexed { index, char ->
                    if (char.isLetter()) {
                        val stackNumber = index / 4
                        val value = line[index]
                        onCharacterFound(stackNumber, value)
                    }
                }
            }

    }
}

fun main() {
    val commands = mutableListOf<Command>()
    val day05 = Day05()

    fun part1(input: List<String>): String {
        val numberOfStacks = day05.numberOfStackes(input)
        val stacks = List(numberOfStacks) { ArrayDeque<Char>() }

        day05.populateStackes(input) { stackNumber, value ->
            stacks[stackNumber].addLast(value)
        }

        commands.clear()
        input.filter { it.contains("move") }.map { commands.add(Command.of(it)) }
        commands.map { step -> repeat(step.count) { stacks[step.to].addFirst(stacks[step.from].removeFirst()) } }

        return stacks.map { it.first() }.joinToString("")
    }

    fun part2(input: List<String>): String {
        val numberOfStacks = day05.numberOfStackes(input)
        val stacks = List(numberOfStacks) { ArrayDeque<Char>() }

        day05.populateStackes(input) { stackNumber, value ->
            stacks[stackNumber].addLast(value)
        }

        commands.clear()
        commands.map { step ->
            if (step.count > 1) {
                val tmp = ArrayDeque<Char>()
                repeat(step.count) {
                    tmp.addFirst(stacks[step.from].removeFirst())
                }
                repeat(step.count) {
                    stacks[step.to].addFirst(tmp.removeFirst())
                }
            } else {
                stacks[step.to].addFirst(stacks[step.from].removeFirst())
            }
        }

        return stacks.map { it.first() }.joinToString("")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
