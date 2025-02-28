package com.example.oopkotlin

class Car (var brandName :String, var year : Int, factoryName:String ,factoryLocation:String) : Factory(factoryName, factoryLocation) {
    private var carPrice : Double? = null
    private var carColor : String? = null
    constructor( brandName: String, year: Int, factoryName: String, factoryLocation: String, carPrice: Double, carColor: String) : this(brandName, year, factoryName, factoryLocation) {
        this.carPrice = carPrice
        this.carColor = carColor
    }


    override fun toString(): String {
        return "Car(BrandName='$brandName', year=$year, factoryName='$factoryName', factoryLocation='$factoryLocation', carPrice=$carPrice, carColor='$carColor')"
    }
    fun calcuLatePrice(){
        if (year > 2025 ){
            carPrice = 300000.0

        }
        else{
            carPrice = 25000.0
        }
    }

}