enum class Gender{
    Male,Female
}
data class Student(val name:String,val age:Int, val grade:Double, val gender:Gender?) {
}