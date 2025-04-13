//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    //belirli bir değerden başlayarak istenilen adımda azalma
    TestLoop.loop2()

    //map key value değeri yazdırma
    TestLoop.loop3()

    /*displaySeparator('!',10)
    println(sum(1,2))
    println(sum2())
    getSound()


    //companion object ile object
    println("Are you good? You can answer yes,y,evet or no,n,hayır")
    val answer : String = readLine().toString()
    println(TestWhenCondition.respondToInput(answer))

    //Singleton yapısı ile Object
    TestWhenMix.mix(Color.RED,Color.BLUE)*/


}
fun getSound(){
    when(val pet = getMyPet("myPet")){
        is Cat -> pet.meow()
        is Dog -> pet.woof()
        else -> println("Unknown Pet")
    }
}
fun displaySeparator(character : Char = '*', size : Int = 10){
    repeat(size){
        print(character)
    }
}
fun sum(a : Int = 0, b : Int = 0, c: Int = 0) = a + b + c
@JvmOverloads
fun sum2(a : Int = 0, b : Int = 0, c: Int = 0) = a + b + c


fun getMyPet(name: String ) : Animal {
    return Cat(name, 10)
}