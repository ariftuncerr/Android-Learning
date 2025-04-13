enum class Gender{
    MALE,
    FEMALE
}
data class Hero (val name:String, val age:Int, val gender: Gender?) {
}