package com.jj.audiolover.di.features

import com.jj.audiolover.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector


/**
 * Created by JJ on 27.01.2018.
 */
@Subcomponent
interface MainActivityComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}