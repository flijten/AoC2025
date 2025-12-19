package day4

import java.io.File
import kotlin.collections.mutableSetOf

data class Position(val lineIndex: Int, val charIndex: Int)
data class GridOutcome(val numberOfAccessiblePaperRolls: Int, val toRemove: MutableSet<Position>)
data class Neighbour(val Position: Position, val character: Char?)

class AdjacencyDetectionAndRemoval {
    private val paperCharacter: String = "@"

    fun getNumberOfAccessiblePaperRolls(): Int {
        var lines = File("src/day4/day4").readLines()
        var numberOfAccessiblePaperRolls = 0
        var gridOutcome = processGrid(lines)

        while (gridOutcome.toRemove.isNotEmpty()) {
            gridOutcome = processGrid(lines)
            numberOfAccessiblePaperRolls += gridOutcome.numberOfAccessiblePaperRolls
            lines = removeAccessiblePaperRolls(lines, gridOutcome)
        }

        return numberOfAccessiblePaperRolls
    }

    private fun removeAccessiblePaperRolls(
        lines: List<String>,
        gridOutcome: GridOutcome
    ): List<String> {
        var newLines = lines
        val grid = newLines.map { it.toMutableList() }

        gridOutcome.toRemove.distinct().forEach { position ->
            grid[position.lineIndex][position.charIndex] = '.'
        }
        newLines = grid.map { it.joinToString("") }
        return newLines
    }

    private fun processGrid(lines: List<String>): GridOutcome {
        var numberOfAccessiblePaperRolls = 0
        val toRemove = mutableSetOf<Position>()

        for (lineIndex in lines.indices) {
            for (charIndex in lines[lineIndex].indices) {
                if (lines[lineIndex][charIndex].toString() == paperCharacter) {

                    // Find neighbors that are '@'
                    val matchingNeighbors = findNeighborPositions(lines, lineIndex, charIndex)

                    // If 3 or fewer, mark those neighbor positions for removal
                    if (matchingNeighbors.size <= 3) {
                        toRemove.add(Position(lineIndex, charIndex))
                        numberOfAccessiblePaperRolls++
                    }

                }
            }
        }
        return GridOutcome(numberOfAccessiblePaperRolls, toRemove)
    }

    private fun findNeighborPositions(
        lines: List<String>,
        lineIndex: Int,
        charIndex: Int
    ): List<Position> {
        val previousLine = lines.getOrNull(lineIndex - 1)
        val currentLine = lines[lineIndex]
        val nextLine = lines.getOrNull(lineIndex + 1)

        val positions = mutableListOf<Position>()

        // Check all 8 neighbors
        val checks = listOf(
            Neighbour(Position(lineIndex - 1, charIndex - 1), previousLine?.getOrNull(charIndex - 1)),
            Neighbour(Position(lineIndex - 1, charIndex), previousLine?.getOrNull(charIndex)),
            Neighbour(Position(lineIndex - 1, charIndex + 1), previousLine?.getOrNull(charIndex + 1)),
            Neighbour(Position(lineIndex, charIndex - 1), currentLine.getOrNull(charIndex - 1)),
            Neighbour(Position(lineIndex, charIndex + 1), currentLine.getOrNull(charIndex + 1)),
            Neighbour(Position(lineIndex + 1, charIndex - 1), nextLine?.getOrNull(charIndex - 1)),
            Neighbour(Position(lineIndex + 1, charIndex), nextLine?.getOrNull(charIndex)),
            Neighbour(Position(lineIndex + 1, charIndex + 1), nextLine?.getOrNull(charIndex + 1))
        )

        checks.forEach { (position, char) ->
            if (char.toString() == paperCharacter) {
                positions.add(position)
            }
        }

        return positions
    }
}