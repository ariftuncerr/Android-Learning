val any : Any? = null

fun safeCast(){
    val lengthOfAny : Int? =  (any as? String)?.length
    println(lengthOfAny)

}