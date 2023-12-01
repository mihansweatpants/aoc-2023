fun main() {
    fun part1(input: List<String>) = input.map(::parseCalibrationValueV1).sum()

    fun part2(input: List<String>) = input.map(::parseCalibrationValueV2).sum()

    val input = readInput("Day01").lines()
    println(part1(input))
    println(part2(input))
}

fun parseCalibrationValueV1(str: String): Int =
    "${str.find { it.isDigit() }}${str.findLast { it.isDigit() }}".toInt()

fun parseCalibrationValueV2(str: String): Int {
    val matchResult = regex.findAll(str)

    val firstMatch = matchResult.first().groupValues.find { it.isNotEmpty() }!!
    val lastMatch = matchResult.last().groupValues.find { it.isNotEmpty() }!!

    return "${firstMatch.toDigit()}${lastMatch.toDigit()}".toInt()
}

val regex = "(?=(one|two|three|four|five|six|seven|eight|nine|\\d))".toRegex()

private fun String.toDigit() = if (this.length == 1) this else digits[this]!!

val digits = mapOf(
    "one" to "1",
    "two" to "2",
    "three" to "3",
    "four" to "4",
    "five" to "5",
    "six" to "6",
    "seven" to "7",
    "eight" to "8",
    "nine" to "9",
)