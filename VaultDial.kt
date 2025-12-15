import javax.swing.text.Position

class VaultDial() {
    private var circularList = CircularList()

    fun turn(instruction: String) {
        val direction = instruction.first().toString()  // or input.first().toString()
        val numberOfPositions = instruction.substring(1).toInt()

        if (direction == "L") {
            circularList.moveBackward(numberOfPositions)
        } else if (direction == "R") {
            circularList.moveForward(numberOfPositions)
        }
    }

    fun numberOfTimesWeHitZero(): Int {
        return circularList.getNumberOfZeroPasses()
    }
}


class CircularList {
    class Node(val value: Int, var next: Node? = null, var prev: Node? = null)

    private var current: Node? = null
    private var numberOfZeroPasses = 0;

    init {
        // Create nodes 0 to 99
        val nodes = (0..99).map { Node(it) }.toTypedArray()

        // Link each node to next and previous
        for (i in 0..99) {
            nodes[i].next = nodes[(i + 1) % 100]
            nodes[i].prev = nodes[(i - 1 + 100) % 100]
        }

        // Start at position 0
        current = nodes[50]
    }

    fun moveForward(steps: Int): Int {
        repeat(steps) {
            current = current?.next
            if (current?.value == 0) {
                numberOfZeroPasses++;
            }
        }
        return current?.value ?: 0
    }

    fun moveBackward(steps: Int): Int {
        repeat(steps) {
            current = current?.prev
            if (current?.value == 0) {
                numberOfZeroPasses++;
            }
        }
        return current?.value ?: 0
    }

    fun getNumberOfZeroPasses(): Int {
        return numberOfZeroPasses
    }
}