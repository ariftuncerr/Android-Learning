package oop_temel
enum class Lesson{
    MATH,ENGLISH,TURKISH,CS
}
open class UniversityMember (
    val memberName : String,
    val id: Int,
    val age: Int,
    val email: String
){
    val universityName: String = "Karabuk-Unı"

    init {
        println("University of :$universityName")
        println("Member Name: $memberName")
        println("Member ID: $id")
        println("Member Email:$email")
    }
}
class Student (memberName: String,
               id: Int,
               age: Int,email: String,
               var lessonsAndGrades: HashMap<Lesson,Int?>
) : UniversityMember (memberName,id,age,email) {

    fun calculateGrade(lesson : Lesson ,midterm : Double, final: Double ) : Int {
        var grade = (final*0.6 + midterm*0.4).toInt()
        lessonsAndGrades.set(lesson,grade)
       return grade
    }

}
fun main(){
    val s1 = Student(
        "arif", 123, 22, "arif@gmail.com",
        hashMapOf(Lesson.MATH to null,Lesson.ENGLISH to null,Lesson.TURKISH to null) )

     println(  s1.calculateGrade(Lesson.MATH,60.0,30.0))

}