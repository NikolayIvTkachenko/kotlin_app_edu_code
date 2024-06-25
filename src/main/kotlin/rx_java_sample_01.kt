import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Executors

fun rxJavaSampleTest01() {
    val observable: Observable<String> = Observable.just("Hello", "World")
    observable.subscribe { value ->
        println(value)
    }
}

//RX JAVA
//Observable
//Observer (Subscriber)
//Operators
//Subscription
//Schedulers

//Type observers
//1-Observable
//2-Single
//3-Completable

//4-Maybe
//5-Flowable

//6-Flow

//Schedulers
//Schedulers.io()
//Schedulers.computation()
//Schedulers.newThread()
//Schedulers.single()
//Schedulers.from(Executor executor)
//AndroidSchedulers.mainThread()

//Subjects - горячий
//1.PublishSubject - не хранит событие
//2.BehaviorSubject - хранит только последнее событие
//3.ReplaySubject - хранит все события
//4.AsyncSubject - хранит и испускает только последнее событие

fun rxJavaSampleTest02() {
    val executors = Executors.newFixedThreadPool(10)

    Observable.just("Test01", "Test02", "Test03", "Test04", "Test05")
        .subscribeOn(Schedulers.io())
        .map {
            println("subscribeOn(Schedulers.io()) Thread.currentThread() = ${Thread.currentThread()}")
            it
        }
        .subscribeOn(Schedulers.computation())
        .map {
            println("subscribeOn(Schedulers.computation()) Thread.currentThread() = ${Thread.currentThread()}")
            it
        }
        .subscribeOn(Schedulers.single())
        .map {
            println("subscribeOn(Schedulers.single()) Thread.currentThread() = ${Thread.currentThread()}")
            it
        }
        .observeOn(Schedulers.from(executors))
        .map {
            println("observeOn(Schedulers.from(executors)) Thread.currentThread() = ${Thread.currentThread()}")
            it
        }
        .subscribe {
            println("subscribe Thread.currentThread() = ${Thread.currentThread()}")
            println("subscribe result = $it")
        }
}

fun rxJavaSampleTest03() {
    println("rxJavaSampleTest03()")
    Observable.just(1, 2, 3, 4, 5, 6, 7)
        .doOnNext { value -> println("Emitting item " + value + " on: " + Thread.currentThread().name) }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation())
        .subscribe(
            { value ->
                println("Consuming item " + value + " on: " + Thread.currentThread().name)

            }, {

            }, {

            }
        )
}

fun rxJavaSampleTest04() {
    println("rxJavaSampleTest04()")
    Observable.just(9, 8, 7, 6, 5, 4, 3, 2, 1)
        .doOnSubscribe {
            println("Schedulers.io()thread name = " +  Thread.currentThread().name)
        }.subscribeOn(Schedulers.io())
        .doOnSubscribe {
            println("Schedulers.computation() thread name = " +  Thread.currentThread().name)
        }.subscribeOn(Schedulers.computation())
        .doOnSubscribe {
            println("Schedulers.newThread() thread name = " +  Thread.currentThread().name)
        }.subscribeOn(Schedulers.newThread())
        .observeOn(Schedulers.single())
        .observeOn(Schedulers.newThread())
        .subscribe {
            println("subscribe thread name = " +  Thread.currentThread().name)
            println("result = $it")
        }

}

