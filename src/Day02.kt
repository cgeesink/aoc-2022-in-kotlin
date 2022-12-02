fun main() {
    fun calculateChoiceScore(myChoice: Char): Int {
        val choiceScore = when (myChoice) {
            'X' -> 1
            'Y' -> 2
            'Z' -> 3
            else -> 0
        }
        return choiceScore
    }

    fun calculateGameScore(opponentChoice: Char, myChoice: Char): Int {
        val gameScore = when (opponentChoice) {
            'A' -> when (myChoice) {
                'X' -> 3
                'Y' -> 6
                else -> 0
            }

            'B' -> when (myChoice) {
                'Y' -> 3
                'Z' -> 6
                else -> 0
            }

            'C' -> when (myChoice) {
                'Z' -> 3
                'X' -> 6
                else -> 0
            }

            else -> throw NotImplementedError()
        }
        return gameScore
    }

    fun part1(input: List<String>): Int {
        var score = 0

        for (line in input) {
            val opponentChoice = line[0]
            val myChoice = line[2]

            val choiceScore = calculateChoiceScore(myChoice)
            val gameScore = calculateGameScore(opponentChoice, myChoice)

            score += choiceScore + gameScore
        }

        return score
    }

    fun part2(input: List<String>): Int {
        var score = 0

        for (line in input) {
            val opponentChoice = line[0]
            val strategyChoice = line[2]
            val myChoice = when (strategyChoice) {
                'X' -> when (opponentChoice) {
                    'A' -> 'Z'
                    'B' -> 'X'
                    'C' -> 'Y'
                    else -> throw NotImplementedError()
                }

                'Y' -> when (opponentChoice) {
                    'A' -> 'X'
                    'B' -> 'Y'
                    'C' -> 'Z'
                    else -> throw NotImplementedError()
                }

                'Z' -> when (opponentChoice) {
                    'A' -> 'Y'
                    'B' -> 'Z'
                    'C' -> 'X'
                    else -> throw NotImplementedError()
                }

                else -> throw NotImplementedError()
            }

            val choiceScore = calculateChoiceScore(myChoice)
            val gameScore = calculateGameScore(opponentChoice, myChoice)

            score += choiceScore + gameScore
        }


        return score
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
