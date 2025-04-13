//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    fun String.lastChar() = get(length - 1)

    fun String.repeat(n: Int): String {
        val sb = StringBuilder(n * length)
        for (i in 1..n) {
            sb.append(this)
        }
        return sb.toString()
    }
    println("Kotlin".repeat(5))

    val c : Char = "Kotlin".lastChar()
}
