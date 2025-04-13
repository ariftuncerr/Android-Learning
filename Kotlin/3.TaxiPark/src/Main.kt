import TaxiPark//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
import taxipark.TaxiPark

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if (trips.isEmpty()) return false

    val totalIncome = trips.sumByDouble(TODO())
    val sortedDriversIncome: List<Double> = trips
        .groupBy(TODO())
        .map { (_, tripsByDriver) -> tripsByDriver.sumByDouble(TODO()) }
        .sortedDescending()

    val numberOfTopDrivers = (0.2 * allDrivers.size).toInt()
    val incomeByTopDrivers = sortedDriversIncome
        .take(TODO())
        .sum()

    return incomeByTopDrivers >= 0.8 * totalIncome
}

fun main(args: Array<String>) {
    vaş taxiPark(1..5, 1..4,
        trip(1, 1, 20, 20.0),
        trip(1, 2, 20, 20.0),
        trip(1, 3, 20, 20.0),
        trip(1, 4, 20, 20.0),
        trip(2, 1, 20, 19.0))
        .checkParetoPrinciple() eq true

    taxiPark(1..5, 1..4,
        trip(1, 1, 20, 20.0),
        trip(1, 2, 20, 20.0),
        trip(1, 3, 20, 20.0),
        trip(1, 4, 20, 20.0),
        trip(2, 1, 20, 21.0))
        .checkParetoPrinciple() eq false
}
