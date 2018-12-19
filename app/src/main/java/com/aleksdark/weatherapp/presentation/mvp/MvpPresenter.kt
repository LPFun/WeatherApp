package com.aleksdark.weatherapp.presentation.mvp

interface MvpPresenter<V : MvpView> {
    fun attachView(mvpView:V)
    fun viewIReady()
    fun detachView()
    fun destroy()
}