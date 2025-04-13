package oopGetterAndSetter

import kotlin.io.path.fileVisitor

class Person2 (val name: String, var age: Int, var phoneNumber: Int){

}
class User (name: String, age:Int, phoneNumber: Int){

    val name: String? = null
        get() = field // if keyword is val only get

    var age: Int? = null
        get() = field
        set(value) {
            if(age in 18..80)
                field = value
            else
                field = null
        }
    var phoneNumber: Int? = null
        get() {
            if (field.toString().length >= 10)
               return field
            else return null
        }
        set(value) {
            if (value.toString().length == 10)
                field = value
            else(
                println("Your phone number length must be 10")
            )
        }

}
fun main(){
    val p1 = Person2("Arif",21,12) // kullanıcının geçersiz girdiği değerler kabul edilebiliyor
    val s1 = User("Arif",22,123) // var ile verilen değerler init kullanılmazsa null' olur
    println(s1.name)
    println(s1.phoneNumber)


}
