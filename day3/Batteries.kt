package day3

import java.io.File

class Batteries {

    fun getTotalJoltage(): Int {

        var totalJoltage: Int = 0;

        File("src/day3/day3").forEachLine { batteryBank ->
            val result = batteryBank.withIndex()
                .maxBy { it.value }

            result?.let { highestNumber ->
                val positionOfHighestNumber = highestNumber.index
                val highestNumberInBank = highestNumber.value.digitToInt()
                var highestJoltage = 0

                if (positionOfHighestNumber == (batteryBank.length - 1)) { // highest number is in the last position, we need to find the next highest
                    val nextHighestNumber = getHighestNumberBelowThreshold(batteryBank, highestNumberInBank)
                    highestJoltage = "$nextHighestNumber$highestNumberInBank".toInt()
                } else {
                    val nextHighestNumber = batteryBank.takeLast(batteryBank.length - positionOfHighestNumber - 1)
                        .map { it.digitToInt() }
                        .max()
                    highestJoltage = "$highestNumberInBank$nextHighestNumber".toInt()
                }

                totalJoltage += highestJoltage
            }
        }

        return totalJoltage;
    }

    private fun getHighestNumberBelowThreshold(string: String, threshold: Int): Int {
        return string
            .map { it.digitToInt() }
            .filter { it < threshold }
            .max()
    }
}