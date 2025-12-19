package day4

import java.io.File

class AdjacencyDetection {
    fun getNumberOfAccessiblePaperRolls(): Int {
        val lines = File("src/day4/day4test").readLines()
        val paperCharacter = "@"
        var numberOfAccessiblePaperRolls = 0

        lines.forEachIndexed { i, line ->
            val previousLine = lines.getOrNull(i - 1)
            val currentLine = line
            val nextLine = lines.getOrNull(i + 1)

            for (index in currentLine.indices) {

                if (currentLine[index].toString() != paperCharacter) {
                    continue
                }

                val neighborsThatContainPaperRoll = listOfNotNull(
                    previousLine?.getOrNull(index - 1)?.takeIf { it.toString() == paperCharacter },  // above-left
                    previousLine?.getOrNull(index)?.takeIf { it.toString() == paperCharacter },      // above
                    previousLine?.getOrNull(index + 1)?.takeIf { it.toString() == paperCharacter },  // above-right
                    currentLine.getOrNull(index - 1)?.takeIf { it.toString() == paperCharacter },    // left
                    currentLine.getOrNull(index + 1)?.takeIf { it.toString() == paperCharacter },    // right
                    nextLine?.getOrNull(index - 1)?.takeIf { it.toString() == paperCharacter },      // below-left
                    nextLine?.getOrNull(index)?.takeIf { it.toString() == paperCharacter },          // below
                    nextLine?.getOrNull(index + 1)?.takeIf { it.toString() == paperCharacter }       // below-right
                )

                if (neighborsThatContainPaperRoll.size < 4) {
                    numberOfAccessiblePaperRolls++
                }
            }
        }

        return numberOfAccessiblePaperRolls
    }
}