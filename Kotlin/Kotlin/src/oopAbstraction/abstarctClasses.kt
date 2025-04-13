package oopAbstraction

abstract class Animal (val name: String, var age: Int){
    abstract fun animalSound() : String // gövdeye sahip olamaz.
}

class Dog(name: String,age: Int) : Animal(name,age){
    override fun animalSound(): String { // bu fonkisyon override edilip initialize edeilmek zorunda
        return "Hav hav"

    }
}
fun main(){
    //val a1 = Animal() // atract classtan nesne oluşturulamaz
    val dog1 = Dog("dog1",1)

}