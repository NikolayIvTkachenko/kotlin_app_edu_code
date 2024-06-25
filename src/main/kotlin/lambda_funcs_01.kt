import java.util.Calendar

fun lambdaTest01() {

    //Long record
    val printMessageInternetCode: (Int) -> Unit
    printMessageInternetCode = {code ->
        println("Network error request with code $code")
    }
    printMessageInternetCode(401);

    //Short record
    {code: Int -> println("Network error request with code $code")} (403)
}

fun lambdaTest02() {
    val calendar: Calendar = Calendar.getInstance()
    val getDaysToEndWeek:() -> Int = fun(): Int = 7 - calendar[Calendar.DAY_OF_WEEK]
    println(getDaysToEndWeek())
}

fun lambdaTest03() {
    val array = arrayOf(1, 2, 3, 4, 5)
    val result = array.map { x -> "value = $x" }
    println(result.joinToString(", "))
}