//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    val action = {person: Person, string: String ->
        sendEmail(person,string)}
    action(Person("John",30),"Hello")

    val lengthOfString = String::length
    println(lengthOfString("Hello World"))

    val greeter = Greeter("Hi")
    println(greeter.greet("John"))

    val isEven : (Int) -> Boolean = {it%2==0}

    val list = listOf(1,2,3,4,5)
    list.filter(isEven).forEach{println(it)}

    val person = Person("Arif",21)

    val agePredicate = Person::isOlder
    println(agePredicate(person,20))








}
fun sendEmail(person: Person, string: String) {
    println("Sending email to ${person.name}")
}
class Greeter(private val greeting: String){
    fun greet(name: String) = "$greeting, $name!"


}
