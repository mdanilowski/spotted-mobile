package pl.mdanilowski.myapplication.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface BaseSchedulers {
    fun io(): Scheduler
    fun computation(): Scheduler
    fun main(): Scheduler
    fun single(): Scheduler
}

class BaseSchedulersImpl @Inject constructor() : BaseSchedulers {

    override fun computation() = Schedulers.computation()

    override fun main() = AndroidSchedulers.mainThread()!!

    override fun io() = Schedulers.io()

    override fun single() = Schedulers.single()
}
