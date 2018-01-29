package com.jj.audiolover

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.jj.audiolover.di.AppComponent
import com.jj.audiolover.di.AppModule
import com.jj.audiolover.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject



/**
 * Created by JJ on 27.01.2018.
 */

class AudioLover : Application(), HasActivityInjector, HasSupportFragmentInjector {


    lateinit var appComponent: AppComponent

    @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingFragmentInjector
    }
}

