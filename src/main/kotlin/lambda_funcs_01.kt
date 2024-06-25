import java.util.Calendar
import kotlin.math.abs
import kotlin.math.max

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

fun lambdaTest04() {
    val array = arrayOf(1, 2, 3, 4, 5)
    val result = array.map { 0..it }.flatten()
    println(result.joinToString(", "))

    val array02 = arrayOf(1, 2, 3, 4, 5)
    val result02 = array02.flatMap { 0..it }
    println(result02.joinToString(", "))
}

//fold - поиски минимума и максимума
fun lambdaTest05() {
    val array = arrayOf(1, 2, 3, 4, 5, 0, -100, 1024, -100)
    val result = array.fold(Int.MIN_VALUE) {acc, n ->
        println("acc: $acc, n: $n")
        max(acc, n)
    }

    println(result)
}

//filter
fun lambdaTest06() {
    val array = arrayOf(1, 2, 3, 4, 5, 0, -100, 1024, -100)
    val result = array.filter { abs(it) < 100 }
    println(result.joinToString ( ", " ))
}