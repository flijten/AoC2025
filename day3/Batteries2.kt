package day3

import java.io.File

class Batteries2 {

    fun getTotalJoltage(): Long {
        var totalJoltage: Long = 0
        val numberOfBatteriesPerBank = 12

        File("src/day3/day3").forEachLine { batteryBank ->
            var lineValue = batteryBank
            var currentBatteries = ""

            for (index in 1..numberOfBatteriesPerBank) {
                var remainingDigits = numberOfBatteriesPerBank - currentBatteries.length

                // find the highest number with at least the needed number of remainingDigits following it
                val result = lineValue.withIndex()
                    .filter { lineValue.length - it.index >= remainingDigits }
                    .maxBy { it.value }

                currentBatteries += result.value ?: ""

                //drop found digit and all before
                lineValue = lineValue.substring(result.index + 1)
                //update remaining digits
                remainingDigits = numberOfBatteriesPerBank - currentBatteries.length

                // remainingDigits is equal to the length ofLinevalue we need all of linevalue
                if (remainingDigits == lineValue.length) {
                    // we need all remaining digits
                    currentBatteries += lineValue
                    totalJoltage += currentBatteries.toLong()

                    return@forEachLine
                }
            }

            totalJoltage += currentBatteries.toLong()
        }

        return totalJoltage;
    }
}