//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val members = listOf('a','b','c')

    println(members.joinToString(separator = "_", prefix = "*", postfix = "*"))

    val list = listOf("Arif","Rauf","Yavuz")
    for((index, element) in list.withIndex()) {
        println("$index: $element")
    }
    val list2 = listOf("Arif","Rauf","Yavuz")
    println(list2.getOrNull(3)) // false
    println(list2.getOrNull(2)) // true

    //tarih formatı regex
    val regex ="""\d{2}\.\d{2}\.\d{4}""".toRegex()
    println("Enter a date with **.**.**** formatting ")
    val date = readln().toString()

    if(regex.matches(date)){
        println("valid formatting")
    }
    else{
        println("invalid formatting")
    }

    //isLetterOrDİgit
    println( 'a'.isLetterOrDigit())


}