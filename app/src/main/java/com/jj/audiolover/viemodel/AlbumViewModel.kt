package com.jj.audiolover.viemodel

import android.arch.lifecycle.ViewModel
import com.jj.audiolover.di.AppComponent

/**
 * Created by JJ on 28.01.2018.
 */
class AlbumViewModel : ViewModel(), AppComponent.Injectable {
    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}