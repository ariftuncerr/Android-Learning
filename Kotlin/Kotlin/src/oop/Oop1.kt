package oop

class Person{
    var name: String = ""
    var age : Int = 0
    var stNumber : Int = 0
    var midtermGrade : Double = 0.0
    var finalGrade : Double = 0.0

    fun calculateAvg(): Double{
        return midtermGrade*0.4 + finalGrade*0.6
    }
    fun printInfos():String {

        return "Name:" +name+ " Student Number:" +stNumber+
                " Note:" + calculateAvg()
    }

}

fun main(){
    val person = Person ()
    person.name = "Arif"
    person.age = 22
    person.finalGrade = 75.0
    person.midtermGrade =60.0
    person.stNumber = 61
    println(person.printInfos())

    val rectangle = Rectangle(12.0,12.0)
    rectangle.num = 20
    rectangle.num = 30
    println(rectangle.counter)


}
class Rectangle(val width : Double , val height : Double){


    var counter = 0
    var num : Int = 1
        get() = field
        set(value) {
            counter++
            field = value
        }

}