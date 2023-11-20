fun main() {
    print("Enter an integer (up to six digits): ")
    val input = readLine()

    try {
        val number = input?.toInt() ?: throw NumberFormatException("Invalid input")

        if (number < 0 || number > 999999) {
            throw IllegalArgumentException("Number out of range (0 - 999999)")
        }

        val result = convertToWords(number)
        println("In words: $result")
    } catch (e: NumberFormatException) {
        println("Invalid input. Please enter a valid integer.")
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
}

fun convertToWords(number: Int): String {
    if (number == 0) {
        return "zero"
    }

    val units = arrayOf("", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val teens = arrayOf("", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
    val tens = arrayOf("", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")

    val million = number / 1000000
    val thousand = (number % 1000000) / 1000
    val remaining = number % 1000

    val millionPart = if (million > 0) "${convertToWords(million)} million " else ""
    val thousandPart = if (thousand > 0) "${convertToWords(thousand)} thousand " else ""
    val remainingPart = if (remaining > 0) convertThreeDigitToWords(remaining) else ""

    return millionPart + thousandPart + remainingPart
}

fun convertThreeDigitToWords(number: Int): String {
    val units = arrayOf("", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val teens = arrayOf("", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
    val tens = arrayOf("", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")

    val hundred = number / 100
    val ten = (number % 100) / 10
    val unit = number % 10

    val hundredPart = if (hundred > 0) "${units[hundred]} hundred " else ""
    val tenPart = if (ten == 1 && unit > 0) teens[unit] else tens[ten]
    val unitPart = if (ten != 1) units[unit] else ""

    return hundredPart + tenPart + unitPart
}
