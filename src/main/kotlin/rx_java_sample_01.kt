import io.reactivex.rxjava3.core.Observable

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

