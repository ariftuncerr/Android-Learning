val sum = {x:Int,y:Int -> x+y}

fun tryLambda(){
    println(sum(5,10))
    addString("Hello"," World")
    val list = listOf(-1,2,3,4,5)

    list.any() { it > 0} // true

}
fun tryLambda2(){
    val list = listOf(-1,2,3,4,5)
    list.any {i: Int -> i > 0} // 0 dan büyük bir değer varsa true döner
    list.any {it < 0} // 0 dan küçük değer varsa true
}
val addString = {str1:String,str2:String -> println(str1+str2) }
fun tryLabmda3(){
    val mapList = mutableMapOf(1 to "Cat", 2 to "Dog")

    mapList.mapValues { (key,value) -> println("$key : $value")}
    mapList.mapValues { entry -> println("${entry.key} : ${entry.value} ")}

}

