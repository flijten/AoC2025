package day2

class InvalidId(var commaSeparatedInput: String) {

    fun sumOfInvalidIds(): Long {
        var sum: Long = 0

        commaSeparatedInput.split(",")
            .forEach { sum += getSumOfInvalidIdsForRange(it) }

        return sum
    }

    private fun getSumOfInvalidIdsForRange(range: String): Long {
        val (startOfRange, endOfRange) = range.split("-").map { it.toLong()}

        var sumOfInvalidIds: Long = 0

        for (integerInRange in startOfRange..endOfRange) {

            if (integerInRange.toString().length %2 != 0) {
                //an uneven number length will never be an invalid ID since it can not have two equal parts
                continue
            }

            val potentialInvalidId = integerInRange.toString()
            val firstHalf = potentialInvalidId.take(potentialInvalidId.length / 2)
            val secondHalf = potentialInvalidId.takeLast(potentialInvalidId.length / 2)

            if (firstHalf == secondHalf) {
                sumOfInvalidIds += integerInRange
            }
        }

        return sumOfInvalidIds
    }
}