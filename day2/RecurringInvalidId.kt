package day2

class RecurringInvalidId(var commaSeparatedInput: String) {

    fun sumOfInvalidIds(): Long {
        var sum: Long = 0

        commaSeparatedInput.split(",")
            .forEach { sum += getSumOfInvalidIdsForRange(it) }

        return sum
    }

    private fun getSumOfInvalidIdsForRange(range: String): Long {
        val (startOfRange, endOfRange) = range.split("-").map { it.toLong()}

        var sumOfInvalidIds: Long = 0

        mainloop@ for (integerInRange in startOfRange..endOfRange) {

            val rangeNumberAsString = integerInRange.toString()

            //test for 111111, 121212, 123123 up to half of the string length
            for(chunkSize in 1..(rangeNumberAsString.length / 2)) {
                if (rangeNumberAsString.chunked(chunkSize).distinct().size == 1) {
                    sumOfInvalidIds += integerInRange
                    continue@mainloop
                }
            }
        }

        return sumOfInvalidIds
    }
}