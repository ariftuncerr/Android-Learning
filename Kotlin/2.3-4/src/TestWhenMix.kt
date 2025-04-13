enum class Color {
    RED, GREEN, BLUE
}
object TestWhenMix {
    fun mix (c1: Color, c2 : Color) =
        when(setOf(c1,c2)){
            setOf(Color.RED, Color.GREEN) -> println("It is yellow")
            setOf(Color.RED, Color.BLUE) -> println("It is violet")
            else -> throw Exception ("Bad Combination")
        }

}