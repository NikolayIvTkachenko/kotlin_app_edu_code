import io.reactivex.rxjava3.core.Observable

fun rxJavaSampleTest01() {
    val observable: Observable<String> = Observable.just("Hello", "World")
    observable.subscribe { value ->
        println(value)
    }
}