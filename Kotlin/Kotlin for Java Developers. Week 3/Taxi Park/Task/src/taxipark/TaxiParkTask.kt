package taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
    allDrivers.filter { d -> trips.none { it.driver == d}}.toSet()




/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
    this.allPassengers.filter { passenger ->
        trips.count { trip -> passenger in trip.passengers } >= minTrips
    }.toSet()


/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
    allPassengers.filter { passenger ->
        trips.count { trip ->
            trip.driver == driver && passenger in trip.passengers
        } > 1
    }.toSet()




/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
    allPassengers.filter { passenger ->
        val passengerTrips = trips.filter { trip -> passenger in trip.passengers }
        val discountedTrips = passengerTrips.count { it.discount != null }
        discountedTrips > passengerTrips.size / 2
    }.toSet()



/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    if (trips.isEmpty()) return null

    return trips
        .map { it.duration / 10 * 10..it.duration / 10 * 10 + 9 } // 0-9, 10-19, vs.
        .groupingBy { it }
        .eachCount()
        .maxByOrNull { it.value }
        ?.key
}


/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if (trips.isEmpty()) return false

    val totalIncome = trips.sumOf { it.cost }
    val incomeByDriver = trips.groupBy { it.driver }
        .mapValues { (_, trips) -> trips.sumOf { it.cost } }
        .values
        .sortedDescending()

    val top20PercentCount = (allDrivers.size * 0.2).toInt().coerceAtLeast(1)
    val top20Income = incomeByDriver.take(top20PercentCount).sum()

    return top20Income >= 0.8 * totalIncome
}
