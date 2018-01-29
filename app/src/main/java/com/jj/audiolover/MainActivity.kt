package com.jj.audiolover

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.View

import com.jj.audiolover.albumlist.AlbumListFragment
import com.jj.audiolover.view.ScreenManager
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import javax.inject.Inject
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import java.lang.ref.WeakReference


class MainActivity : DaggerAppCompatActivity() {
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var screenManager: ScreenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            screenManager.activity = WeakReference(this)
            screenManager.showInitialFragment(AlbumListFragment())
        }
    }

    override fun onResume() {
        super.onResume()
        screenManager.activity = WeakReference(this)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }
}
