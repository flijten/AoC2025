import java.io.File

fun main() {
    val VaultDial = VaultDial();
//    println(System.getProperty("user.dir"))
    File("src/day1").forEachLine { instruction ->
        VaultDial.turn(instruction)
    }

//    VaultDial.turn("R261");
    println(VaultDial.numberOfTimesWeHitZero())
}


