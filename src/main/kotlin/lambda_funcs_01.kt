import java.util.*
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

fun lambdaTest07() {
    val inputText = "At the age of twenty, Susan Calvin had been part of the particular Psycho-Math\n" +
            "seminar at which Dr. Alfred Lanning of U. S. Robots had demonstrated the first mobile\n" +
            "robot to be equipped with a voice. It was a large, clumsy unbeautiful robot, smelling of\n" +
            "machine-oil and destined for the projected mines on Mercury. But it could speak and\n" +
            "make sense."
    var words = mutableListOf<String>()
    var currentWord = ""
    for(i in inputText.indices) {
        if (i == inputText.length - 1){
            currentWord +=inputText[i]
            words.add(currentWord)
        }
        if(inputText[i] == ' '){
            words.add(currentWord)
            currentWord = ""
        } else {
            currentWord += inputText[i]
        }
    }

    for (i in words.indices) {
        words[i] = words[i].lowercase(Locale.getDefault())
        if(words[i].endsWith('.') || words[i].endsWith(',')) {
            words[i] = words[i].substring(0, words[i].length - 2)
        }
    }

    var shortestLength = Int.MAX_VALUE
    for(i in words.indices) {
        if (words[i].length < shortestLength) {
            shortestLength = words[i].length
        }
    }

    var shortestWords = mutableListOf<String>()
    for(i in words.indices) {
        if (words[i].length == shortestLength) {
            shortestWords.add(words[i])
        }
    }

    val repeatingWords = mutableMapOf<String, Int>()
    for(i in words.indices) {
        val currentCount = repeatingWords[words[i]] ?: 0
        repeatingWords[words[i]] = currentCount + 1
    }

    for (k in repeatingWords.keys) {
        println("$k: ${repeatingWords[k]}")
    }
    println("---")
    println(shortestWords.joinToString(", "))
}

fun lambdaTest08() {
    val inputText = "At the age of twenty, Susan Calvin had been part of the particular Psycho-Math\n" +
            "seminar at which Dr. Alfred Lanning of U. S. Robots had demonstrated the first mobile\n" +
            "robot to be equipped with a voice. It was a large, clumsy unbeautiful robot, smelling of\n" +
            "machine-oil and destined for the projected mines on Mercury. But it could speak and\n" +
            "make sense."

    fun filterEnding(c: Char): (String) -> String = { s -> s.takeIf { it.endsWith(c) }?.dropLast(1) ?: s }
    val words = inputText.split(" ").map(String::toLowerCase).map(filterEnding(',')).map(filterEnding('.'))
    val wordsCount = words.groupingBy { it }.eachCount()
    val shortestLength = words.minBy { it.length } ?.length
    val shortestWords = words.filter { it.length == shortestLength }

    wordsCount.map { (k, v) -> "$k: $v" }.forEach(::println)
    println("---")

    println(shortestWords.joinToString(", "))
}