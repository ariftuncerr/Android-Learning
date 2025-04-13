
fun main() {
    println("First ${foo()} Second ${foo()}")
}

fun foo(): String {
    println("Calculating foo...")
    return "foo"
}
