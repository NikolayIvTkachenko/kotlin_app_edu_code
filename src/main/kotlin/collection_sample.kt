
fun createCollection() {
    //Java
    val list = ArrayList<String>()



    //Kotlin
    val listKotlin = listOf("111", "222", "333") //arraylist //unmutable list
    val text0 = listKotlin.get(0)
    val text1 = listKotlin[1]

    val listKotlinMutable = mutableListOf("111", "222", "333") //mutable list
    listKotlinMutable.add("444")

    println(listKotlinMutable)

    val setKotlin = setOf<Int>(1, 2, 3, 1)
    println(setKotlin)


    val setMutableKotlin = mutableSetOf<Int>()
    setMutableKotlin.add(22)
    setMutableKotlin.add(11)
    setMutableKotlin.add(44)
    setMutableKotlin.add(33)
    setMutableKotlin.add(11)
    println(setMutableKotlin)

    val mapKotlin = mapOf("key1" to "value1", "key2" to "value2")
    println(mapKotlin.get("key2"))

}
//KOTLIN
//          MutableIterable  <-----  Iterable
//                  |                   |
//         MutableCollection <----- Collection          Map
//           |                        |                  |
//           |                        |_List         MutableMap
//           |_MutableList            |_Set
//           |
//           |_MutableSet

//JAVA
//              List              Set            Map
//              |                 |              |
//              |_ArrayList       |_HashSet      |_HashMap
//              |_LinkedList      |_TreeSet      |_TreeMap
//