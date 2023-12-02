import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        val cubesAvailable = mapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14
        )

        return input.withIndex()
            .filter { (_, line) -> isValidGame(line, cubesAvailable) }
            .sumOf { (index, _) -> index + 1 }
    }

    fun part2(input: List<String>): Int =
        input.map(::powerOfMinSet).sum()

    val input = readInput("Day02").lines()
    println(part1(input))
    println(part2(input))
}

fun isValidGame(line: String, cubesAvailable: Map<String, Int>): Boolean {
    val rounds = line.substring(line.indexOf(":") + 2).split("; ")

    for (round in rounds) {
        val cubesRevealed = round.split(", ")
        for (countAndColor in cubesRevealed) {
            val (count, color) = countAndColor.split(" ")
            if (cubesAvailable[color]!! < count.toInt()) return false
        }
    }

    return true
}

fun powerOfMinSet(line: String): Int {
    val rounds = line.substring(line.indexOf(":") + 2).split("; ")

    val maxRevealedCount = mutableMapOf<String, Int>()

    for (round in rounds) {
        val cubesRevealed = round.split(", ")
        for (countAndColor in cubesRevealed) {
            val (count, color) = countAndColor.split(" ")
            maxRevealedCount[color] = max(count.toInt(), maxRevealedCount[color] ?: 0)
        }
    }

    return maxRevealedCount.values.reduce(Int::times)
}
