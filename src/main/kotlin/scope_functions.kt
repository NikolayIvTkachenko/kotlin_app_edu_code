import Struct.Student

//Kotlin scope functions
//Let, Also, Apply, Run, With

fun scopeFunctionTest() {

    //Let
    var number: Int? = null
    number = 7
    val xx = number?.let {
        val number02 = it + 2
        println(number02)
        number02
    } ?: 55
    println(xx)


    //Also (something else)
    var n = 5
    fun getSquaredValue() = (n * n).also { n++ }
    println(getSquaredValue())
    println(n)


    //Apply
    val student01 = Student("Alex", 22)
    println(student01)
    student01.apply {
        name = "Ivan"
        age = 18
    }
    println(student01)


    //Run
    val student02 = student01.run {
        name = "helen"
        age = 19
        this
    }

    println(student01)
    println(student02) //if not use this , return => kotlin.Unit

    //With
    var student04 = Student("Atom", 17)
    println(student04)
    with(student04) {
        name = "Proton"
    }
    println(student04)

}



