package oop

// private = sadece içerisindeki sınıf erişebilir
// public = her yerden erişilebilir
// protected = child class ve kendi classlarından erişilebilir
// internal = aynı modül içerisinden erişilebilir

class A{
    private var name : String? = null
    protected var age : String? = null
    public var birthYear: Int? = null
}
open class C{
    protected val name: String? = null

}
class D : C (){
    // protected olan erişilebilir.


}
internal class Car {
    val carName : String? = null
}
fun main(){
    var a = A()
    a.birthYear //erişilebilir
    // a. protected ve private olanlar erişilemez
}