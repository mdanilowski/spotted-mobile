package pl.mdanilowski.spotted.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface BaseSchedulers {
    fun io(): Scheduler
    fun computation(): Scheduler
    fun main(): Scheduler
    fun single(): Scheduler
}

class BaseSchedulersImpl constructor() : BaseSchedulers {

    override fun computation() = Schedulers.computation()

    override fun main() = AndroidSchedulers.mainThread()!!

    override fun io() = Schedulers.io()

    override fun single() = Schedulers.single()
}
