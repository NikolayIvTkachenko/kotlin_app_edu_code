import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import java.lang.Exception
import java.util.concurrent.Executors

//https://habr.com/ru/articles/739212/
//https://habr.com/ru/companies/badoo/articles/328434/

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

//Subjects - горячий Hot Observables
//1.PublishSubject - не хранит событие
//2.BehaviorSubject - хранит только последнее событие
//3.ReplaySubject - хранит все события
//4.AsyncSubject - хранит и испускает только последнее событие

//Subject —  тот, кто публикует новые сообщения;
//
//Observer —  тот, кто на них подписан. В реактивных потоках подписчик обычно называется Subscriber. Термины разные,
// но, по сути, это одно и то же. В большинстве сообществ более привычны термины Publisher/Subscriber.

//PublishSubject
//Эмитирует только те элементы, которые были добавлены после подписки наблюдателя.
// Новые подписчики получают только последующие элементы после своей подписки.
// Не сохраняет предыдущие элементы и не повторяет их для новых подписчиков.
// Если подписчик пропускает события, он не получит их после своей подписки

//Observable — это «наблюдаемое».
// Так называется поток информации, источник данных и событий. Когда в Observable меняются данные, об этом узнает Observer.
//
//Observer — это «наблюдатель», обработчик событий.
// наблюдает за потоком данных и выполняет действия, как только те изменились.
// Observable посылает ему сигнал, когда начинает или заканчивает выдавать информацию.

//!!!
//1- ни одно событие не будет выдано после того, как последовательность завершена (onError или onCompleted)
//

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

fun rxJavaSampleTest05() {
    println("rxJavaSampleTest05()")

    val subject001 = PublishSubject.create<Int>()

    subject001.onNext(1)

    val disposal = subject001.subscribe(
        {
            println("next = $it")
        },
        {
            println("throwable = $it")
        },
        {
            println("Completed")
        }
    )

    subject001.onNext(2)
    subject001.onNext(3)
    subject001.onNext(4)
    subject001.onNext(5)

    subject001.onError(Exception("Error handler"))
    subject001.onNext(6)
    subject001.onComplete()

    disposal.dispose()
    //rxJavaSampleTest05()
    //next = 2
    //next = 3
    //next = 4
    //next = 5
    //throwable = java.lang.Exception: Error handler
//Важно отметить, что мы не увидим сообщение «Последовательность завершена»,
// потому что после генерации ошибки с помощью метода onError() последовательность
// PublishSubject считается завершенной и дальнейшие события не будут доставлены наблюдателям.
}


//BehaviorSubject
//Горячий источник, который хранит последний элемент и эмитирует его немедленно при подписке нового наблюдателя.
fun rxJavaSampleTest06() {
    println("rxJavaSampleTest06()")
    val behaviorSubject = BehaviorSubject.create<Int>()
    behaviorSubject.onNext(0)
    behaviorSubject.onNext(1)
    behaviorSubject.onNext(2)
    behaviorSubject.subscribe({
        println("subscriber 1, next = $it")
    }, {
        println("subscriber 1, throwable = $it")
    }, {
        println("subscriber 1, Completed")
    })
    behaviorSubject.onNext(3)
    behaviorSubject.onNext(4)
    behaviorSubject.subscribe({
        println("subscriber 2, next = $it")
    }, {
        println("subscriber 2, throwable = $it")
    }, {
        println("subscriber 2, Completed")
    })
    behaviorSubject.onNext(5)
    behaviorSubject.onNext(6)
    //rxJavaSampleTest06()
    //subscriber 1, next = 2
    //subscriber 1, next = 3
    //subscriber 1, next = 4
    //subscriber 2, next = 4
    //subscriber 1, next = 5
    //subscriber 2, next = 5
    //subscriber 1, next = 6
    //subscriber 2, next = 6
}


//ReplaySubject
// имеет специальную возможность кэшировать все поступившие в него данные.
// Когда у него появляется новый подписчик, последовательность выдана ему начиная с начала.
// Все последующие поступившие данные будут выдаваться подписчикам как обычно.
fun rxJavaSampleTest07() {
    println("rxJavaSampleTest07()")
    val relaySubject003 = ReplaySubject.create<Int>()
    relaySubject003.subscribe({
        println("subscriber 1, next = $it")
    }, {
        println("subscriber 1, throwable = $it")
    }, {
        println("subscriber 1, Completed")
    })
    relaySubject003.onNext(0)
    relaySubject003.onNext(1)
    relaySubject003.onNext(2)
    relaySubject003.subscribe({
        println("subscriber 2, next = $it")
    }, {
        println("subscriber 2, throwable = $it")
    }, {
        println("subscriber 2, Completed")
    })
    relaySubject003.onNext(3)
    relaySubject003.onNext(4)
    //Thread.currentThread() = Thread[#1,main,5,main]
    //rxJavaSampleTest07()
    //subscriber 1, next = 0
    //subscriber 1, next = 1
    //subscriber 1, next = 2
    //subscriber 2, next = 0
    //subscriber 2, next = 1
    //subscriber 2, next = 2
    //subscriber 1, next = 3
    //subscriber 2, next = 3
    //subscriber 1, next = 4
    //subscriber 2, next = 4


    println("subscriber 3")
    val relaySubject0031 = ReplaySubject.createWithSize<Int>(2)
    relaySubject0031.onNext(0)
    relaySubject0031.onNext(1)
    relaySubject0031.onNext(2)
    relaySubject0031.onNext(3)
    relaySubject0031.onNext(4)
    relaySubject0031.onNext(5)
    relaySubject0031.subscribe({
        println("subscriber 3, next = $it")
    }, {
        println("subscriber 3, throwable = $it")
    }, {
        println("subscriber 3, Completed")
    })
    //subscriber 3
    //subscriber 3, next = 4
    //subscriber 3, next = 5

}

//AsyncSubject
// также хранит последнее значение. Разница в том, что он не выдает
// данных до тех пока не завершится последовательность.
// Его используют, когда нужно выдать единое значение и тут же завершиться.

fun rxJavaSampleTest08() {
    println("rxJavaSampleTest08()")

    val asyncSubject01 = AsyncSubject.create<Int>()
    asyncSubject01.onNext(0)
    asyncSubject01.onNext(1)
    asyncSubject01.onNext(2)
    //если не вызваем asyncSubject01.onCompleted(), то ничего не будет выведено.

    val asyncSubject02 = AsyncSubject.create<Int>()
    asyncSubject02.onNext(0)
    asyncSubject02.onNext(1)
    asyncSubject02.onNext(2)
    asyncSubject02.onComplete()

    asyncSubject01.subscribe({
            println("subscriber 1, next = $it")
        }, {
            println("subscriber 1, throwable = $it")
        }, {
            println("subscriber 1, Completed")
        })
    //Ничего не получает

    asyncSubject02.subscribe({
        println("subscriber 2, next = $it")
    }, {
        println("subscriber 2, throwable = $it")
    }, {
        println("subscriber 2, Completed")
    })
    //rxJavaSampleTest08()
    //subscriber 2, next = 2
    //subscriber 2, Completed

}