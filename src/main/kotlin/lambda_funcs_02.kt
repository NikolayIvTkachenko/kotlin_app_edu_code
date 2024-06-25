

var lambda01 = {param: Int -> param * 2}
var lambda02: (Int, Int, Boolean) -> Unit = {param1: Int, param2: Int, param3: Boolean ->
    if (param3) {
        println(param1)
    } else {
        println(param2)
    }
}

fun lambdaTestFunc01() {
    var a0 = lambda01(6)
    println(a0)

    var a1 = lambda01.invoke(5)
    println(a1)
}

fun lambdaTestFunc02() {
    var a3 = lambda02(6, 9, true)
    var lambda03 = { println("Test 007") }
    lambda03()
}

fun lambdaTestFunc03() {
    var aa = 30

    var lambda05: () -> Unit = {
        //aa++
        aa=aa*2
    }
    println(aa)
    //lambda05.invoke()
    lambda05()
    println(aa)

}