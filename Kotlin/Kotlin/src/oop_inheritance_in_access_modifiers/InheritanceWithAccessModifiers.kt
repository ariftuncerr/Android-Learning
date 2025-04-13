package oop_inheritance_in_access_modifiers

/*open class Person (
    public val name : String,
    protected val age : Int,
    private val id : Int
){
    override fun toString(): String {
        return "Name: $name Age: $age, ID: $id"
    }
}

class Student1 (
    name: String,
    age: Int,
    id: Int,
    val studentEmail: String
): Person (name,age,id){

    fun showAge() : Unit {
        println(age)  // protected oluduğu için yalnızca subClasstan erişilebilir.Mainde erişilemeez!!!
    }
    fun showId(): Unit{
        println(id) // id değeri private olduğu için alt sınıftan erişilemez.

    }

    override fun toString(): String {
        return super.toString()+"Student Email: $studentEmail"
    }
}

fun main(){
    val p1 = Person("Arif",22,21)
    val s1 = Student1("Rauf",19,123,"rauf@gmail.com")

    p1.name // erişilebilir default tanımlanan değer zaten public
    p1.age // ulaşılamaz // age is protected in 'Person' only subclasses access
    p1.id // ulaşılamaz  // id is private in 'Person' only it access


    s1.studentEmail // public erişim mümkün
    s1.age // age is private in 'Person'

    println(s1) // super -- yapısı örneği

}
*/