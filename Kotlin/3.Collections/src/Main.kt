//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val studentList = listOf(
        Student("Arif",22,3.4,Gender.Male),
        Student("Ömer",21,3.5,Gender.Male),
        Student("Ayşenur",22,3.6,Gender.Female),
        Student("Yaren",22,3.6,null),
        Student("Yavuz",16,3.0,Gender.Male)


    )
   /* println(studentList)
    val older18List = studentList.filter { it.age > 18 }
    println(older18List)
    val younger18List = studentList.filterNot { it.age <= 18 }
    println(younger18List)
    val groupedByGender = studentList.groupBy { it.gender }
    println(groupedByGender)

    val squaredGradeList = studentList.map { it.grade * it.grade }
    println(squaredGradeList)

    val isGreaterThan3 = studentList.all { it.grade > 3 } // false or true
    println(isGreaterThan3)

    val isAnyGradeGreaterThan3 = studentList.any { it.grade > 3 } // false or true
    println(isAnyGradeGreaterThan3)

    val graterThan35 = studentList.count {it.grade > 3.5}
    println(graterThan35)

    val higherGradeStudentName = studentList.maxByOrNull { it.grade }!!.name

    println(higherGradeStudentName)
    val lowerGradeStudentName = studentList.minByOrNull { it.grade }!!.name
    println(lowerGradeStudentName)

    val (list1,list2) = studentList.partition {it.age > 21}
    for (student in list1){
        println(student.name)
    }
    for (student in list2){
        println(student.name)
    }*/

    /*studentList.map {
        if(it.gender == null)
            it.grade*0
        else
            it.grade*it.grade
    }
    println( studentList.associateBy { it.age })*/

    val list1 = listOf(1, 2, 3)
    println( list1.allNonZero())
    println( list1.allNonZero1())
    println( list1.allNonZero2())

   /* list1.containsZero()
        list1.containsZero1()
    list1.containsZero2()

    val list2 = listOf(0, 1, 2)
    list2.allNonZero()
    list2.allNonZero1()
    list2.allNonZero2()

    list2.containsZero()
    list2.containsZero1()
    list2.containsZero2()*/











}
fun List<Int>.allNonZero() = all {
    if(it == 0)
        false
    else
       true
}
fun List<Int>.allNonZero1() =  none {it.equals(0)}
fun List<Int>.allNonZero2() =  any { it.equals(0) == false }

fun List<Int>.containsZero() =  any { it.equals(0) == true }
fun List<Int>.containsZero1() =  all {it>0 || it<0}
fun List<Int>.containsZero2() =  none {it.equals(0) == false }