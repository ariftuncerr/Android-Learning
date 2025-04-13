object TestLoop {
    fun loop() {
        for (i in 1..10) {
            println(i)
        }
    }
    fun loop2() {
        for (i in 10 downTo 1 step 3){
            println(i)
        }
    }
    val map = mapOf(1 to "one", 2 to "two", 3 to "three")
    fun loop3() {
        for((key,value) in map){
            println("$key = $value")
        }
    }



}