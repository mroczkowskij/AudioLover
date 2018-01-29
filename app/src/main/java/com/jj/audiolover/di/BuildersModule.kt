package com.jj.audiolover.di

import android.app.Activity
import android.support.v4.app.Fragment

import com.jj.audiolover.MainActivity
import com.jj.audiolover.albumlist.AlbumListFragment
import com.jj.audiolover.di.features.AlbumListFragmentComponent
import com.jj.audiolover.di.features.MainActivityComponent

import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module
abstract class BuildersModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    internal abstract fun bindFeatureActivityInjectorFactory(builder: MainActivityComponent.Builder): AndroidInjector.Factory<out Activity>


    @Binds
    @IntoMap
    @FragmentKey(AlbumListFragment::class)
    internal abstract fun bindFeatureFragmentInjectorFactory(builder: AlbumListFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    // Add more bindings here for other sub components
}