import java.time.LocalDate

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    /*println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false*/

    //tarih aralık kontrolü örneği
    val startDate = LocalDate.of(2007,1,1)
    val endDate = LocalDate.now()
    val myBirthDate = LocalDate.of(2003,5,2)

    val Under18age = if (myBirthDate in startDate..endDate) true else false
    println(Under18age)



}
fun isValidIdentifier(s: String): Boolean {
    if(s.isEmpty())
        return false
    if(!(s.first() in 'a'..'z' || s.first() in 'A'..'Z' || s.first() == '_'))
        return false
    for(c in s){
        if(!(c.isLetterOrDigit() || c == '_'))
            return false
    }
    return true
}
