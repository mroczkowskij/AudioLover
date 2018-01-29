package com.jj.audiolover.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.jj.audiolover.AudioLover
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val application: AudioLover) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val t = super.create(modelClass)
        if (t is AppComponent.Injectable) {
            (t as AppComponent.Injectable).inject(application.appComponent)
        }
        return t
    }
}