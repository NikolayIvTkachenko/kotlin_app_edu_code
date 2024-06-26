import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.ArrayList

//implementation 'io.reactivex.rxjava2:rxjava:2.1.9'подсвечено

fun rxJavaTest001() {
    println("rxJavaTest001()")

    val observable01 = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val observable02 = Observable.just("Item 1", "Item 2", "Item 3", "Item 4", "Item 5",
        "Item 6", "Item 7", "Item 8", "Item 9", "Item 10")
    val observable03 = Observable.just("0-111", "0-222", "0-333", "0-444", "0-555",
        "0-666", "0-777", "0-888", "0-999", "0-101010")
    println()
    println("flatMap")
    observable01.flatMap {value ->
        observable02
    }.subscribe { result ->
        println("result = $result")
    }
    //Thread.currentThread() = Thread[#1,main,5,main]
    //rxJavaTest001()
    //result = Item 1
    //result = Item 2
    //result = Item 3
    //result = Item 4
    //result = Item 5
    //result = Item 6
    //result = Item 7
    //result = Item 8
    //result = Item 9
    //result = Item 10
    //result = Item 1
    //result = Item 2
    //result = Item 3
    //result = Item 4
    //result = Item 5
    //result = Item 6
    //result = Item 7
    //result = Item 8
    //result = Item 9
    //result = Item 10
    //result = Item 1
    //result = Item 2
    //result = Item 3
    //result = Item 4
    //result = Item 5
    //result = Item 6
    //result = Item 7
    //result = Item 8
    //result = Item 9
    //result = Item 10
    //result = Item 1
    //result = Item 2
    //result = Item 3
    //result = Item 4
    //result = Item 5
    //result = Item 6
    //result = Item 7
    //result = Item 8
    //result = Item 9
    //result = Item 10
    //result = Item 1
    //result = Item 2
    //result = Item 3
    //result = Item 4
    //result = Item 5
    //result = Item 6
    //result = Item 7
    //result = Item 8
    //result = Item 9
    //result = Item 10
    //result = Item 1
    //result = Item 2
    //result = Item 3
    //result = Item 4
    //result = Item 5
    //result = Item 6
    //result = Item 7
    //result = Item 8
    //result = Item 9
    //result = Item 10
    //result = Item 1
    //result = Item 2
    //result = Item 3
    //result = Item 4
    //result = Item 5
    //result = Item 6
    //result = Item 7
    //result = Item 8
    //result = Item 9
    //result = Item 10
    //result = Item 1
    //result = Item 2
    //result = Item 3
    //result = Item 4
    //result = Item 5
    //result = Item 6
    //result = Item 7
    //result = Item 8
    //result = Item 9
    //result = Item 10
    //result = Item 1
    //result = Item 2
    //result = Item 3
    //result = Item 4
    //result = Item 5
    //result = Item 6
    //result = Item 7
    //result = Item 8
    //result = Item 9
    //result = Item 10
    //result = Item 1
    //result = Item 2
    //result = Item 3
    //result = Item 4
    //result = Item 5
    //result = Item 6
    //result = Item 7
    //result = Item 8
    //result = Item 9
    //result = Item 10


    //Merge
    println()
    println("Merge")
    observable02.mergeWith(observable03)
        .subscribeOn(Schedulers.single())
        .subscribe(
            {word ->
                println("result = $word")
            },
            {

            },
            {

            }
        )

    println()
    println("zip")
    Observable.zip(observable02, observable03, BiFunction<String, String, String> { s1, s2 ->
        "$s1 __ $s2 "
    }).subscribeOn(Schedulers.single())
        .subscribe(
            {word ->
                println("result = $word")
            },
            {

            },
            {

            }
        )
    //result = Item 1 __ 0-111
    //result = Item 2 __ 0-222
    //result = Item 3 __ 0-333
    //result = Item 4 __ 0-444
    //result = Item 5 __ 0-555
    //result = Item 6 __ 0-666
    //result = Item 7 __ 0-777
    //result = Item 8 __ 0-888
    //result = Item 9 __ 0-999
    //result = Item 10 __ 0-101010

    println()
    println("zipWith")
    observable02.zipWith(observable03){ s1, s2 ->
        "$s1 _zipWith_ $s2 "
    }.subscribeOn(Schedulers.single())
        .subscribe(
            {word ->
                println("result = $word")
            },
            {

            },
            {

            }
        )
//result = Item 1 _zipWith_ 0-111
//result = Item 2 _zipWith_ 0-222
//result = Item 3 _zipWith_ 0-333
//result = Item 4 _zipWith_ 0-444
//result = Item 5 _zipWith_ 0-555
//result = Item 6 _zipWith_ 0-666
//result = Item 7 _zipWith_ 0-777
//result = Item 8 _zipWith_ 0-888
//result = Item 9 _zipWith_ 0-999
//result = Item 10 _zipWith_ 0-101010

}

fun rxJavaTest002() {
    println("rxJavaTest002()")
    val words = arrayOf(
        "the",
        "quick",
        "brown",
        "fox",
        "jumped",
        "over",
        "the",
        "lazy",
        "dog"
    )

    val words02 = ArrayList<String>().apply {
        add("the")
        add("quick")
        add("brown")
        add("fox")
        add("jumped")
        add("over")
        add( "the")
        add("lazy")
        add("dog")
    }



    Observable.fromArray(words)
        //.observeOn(Schedulers.single())
        .subscribe(
            {word ->
                println("result = $word")
            },
            {

            },
            {

            }
        )
    //Вывод kotlin коллекции - words
    //rxJavaTest002()
    //result = [Ljava.lang.String;@1996cd68

    //Вывод java коллекции - words02
    //rxJavaTest002()
    //result = [the, quick, brown, fox, jumped, over, the, lazy, dog]

}

fun rxJavaTest003() {
    println("rxJavaTest003()")
    val valuesInt = Observable.range(10, 15)

}