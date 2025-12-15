package day2

import java.io.File

fun main() {

//    assignment 1
//    var InvalidId = InvalidId(File("src/day2/day2").readText())
//
//    println(InvalidId.sumOfInvalidIds());

    var RecurringInvalidId = RecurringInvalidId(File("src/day2/day2").readText())

    println(RecurringInvalidId.sumOfInvalidIds());
}
