package oopAbstraction

import kotlin.io.path.fileVisitor
open class Device(
    protected val name: String,
    protected val devicePrice: Double,
    protected val deviceType : String,
    override val kWEnergy: Double,
): PublicFeatures{
    override fun turnOnTurnOff(select: Int): Boolean {
        if(select == 0) return false
        else return true
    }

}
interface PublicFeatures{
    val kWEnergy : Double
    companion object{
        val kWPrice = 1
    }

    fun turnOnTurnOff(select: Int) : Boolean
    fun energyConsumption() : Double {
        return kWEnergy*kWPrice;
    }

}

interface NetworkConnection{
    var networkName : String?
    var networkIP : Int?
    fun connection () : Boolean {
        if(networkName != null && networkIP !=null)
            return true
        else return false
    }

}
interface SelectableColor{
    var color : String
    fun changeColor(newColor : String){
        color = newColor
    }
}
class Clima (name: String,
             devicePrice: Double,
             deviceType: String,
             kWEnergy: Double,
): Device(name,devicePrice,deviceType,kWEnergy){

}

class Tv (name: String,
          devicePrice: Double,
          deviceType: String,
          kWEnergy: Double,
          override var networkName: String?,
          override var networkIP: Int?,
): Device(name,devicePrice,deviceType,kWEnergy),NetworkConnection{


}
fun main(){
    val device1 = Device("Vestel Buzdolabı",1000.0,"Buzdolabı",134.0)
    device1.turnOnTurnOff(0)
    println("device 1: Energy Consumption:"+device1.energyConsumption())

    val tv1 = Tv("Samsung TV1",6000.0,"Tv",120.0,null, null)
    println("TV Energy Consumption:"+tv1.energyConsumption())
    tv1.networkName = "Turknet1213"
    tv1.networkIP = 12345
    println("TV connection:"+tv1.connection())
    val clima1 = Clima("klima1",1234.0,"Klima",200.0)
    println("Klima 1 is Open:"+clima1.turnOnTurnOff(0).toString())




}


