
val heros = listOf(
    Hero("Batman",60, Gender.MALE),
    Hero("Superman",42,Gender.MALE),
    Hero("The Kind",9,null),
    Hero("Lady Hulk",29,Gender.FEMALE),
    Hero("First Mate", 29, Gender.MALE),
    Hero("Sir Stephen", 37, Gender.MALE))

)
val mapByAge = heros.groupBy { it.age }
val (age,group) = mapByAge.maxBy { (_,group) -> group.size }!!
fun hello(){
    println()
}