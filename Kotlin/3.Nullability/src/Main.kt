import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Boolean

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
   /* val s1 : String = "Hello"
    val s2 : String? = null
    println(s1.length)
    println(s2?.length) // ifadenin null kabul etmesi için ? gerekli

    //var length : Int = if(s2 != null) s2.length else 0
    var length = s2?.length ?: 0 // uzunluk değeri null ise 0 yap
    println(length)

    // println(foo(1)!!) // null olabilecek bir değeri kesinlikle null değil gibi kullandın

    var a : Int? = null
    var b : Int = 2
    var c : Int? = 3

    val list : List<Int?> = listOf(a,b,c)
    println(list.size)

    val list2 : List<Int>? = listOf()
    println(list2?.size) // ? gereklidir değilse error

    val i : Int? = list.get(0) // verilen sonuç null olabilir ise Int? gelmeli
    val j : Int? = list2?.get(0) // hem list2? gelmeli hem Int? gelmeli

            val s11: String? = null
            val s22: String? = ""
            println(s11.isNullOrEmpty())
            println(s22.isNullOrEmpty())

            val s3 = "   "
            println(s3.isNullOrEmpty())


   // String? türünde bir 'isEmptyOrNull()' uzantı fonksiyonu ekleyin ve uygulayın. Dize null veya boşsa true döndürmelidir.
    */
    safeCast()



}
fun String?.isEmptyOrNull(): Boolean = this == null || this.length == 0

fun foo(a: Int = 0, b: String = ""): Int? {
    if(a == 0)
        return 0
    else
        return null
}


