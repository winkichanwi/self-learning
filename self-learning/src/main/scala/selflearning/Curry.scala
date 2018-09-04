package selflearning

object Curry extends App {
    val add = {(x: Int, y: Int, z: Int) => x + y + z}
    val addCurried = add.curried
    println(add(1, 2, 3))
    println(addCurried(1))
}
