package com.aleksdark.weatherapp.mvp

interface MvpPresenter<V : MvpView> {
    fun attachView(mvpView:V)
    fun viewIReady()
    fun detachView()
    fun destroy()
}