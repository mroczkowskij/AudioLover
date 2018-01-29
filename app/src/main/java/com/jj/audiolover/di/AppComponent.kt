package com.jj.audiolover.di;

import com.jj.audiolover.AudioLover
import com.jj.audiolover.viemodel.AlbumListViewModel
import com.jj.audiolover.viemodel.AlbumViewModel
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AndroidSupportInjectionModule::class, AppModule::class, BuildersModule::class))
interface AppComponent {
    fun inject(app: AudioLover)
    fun inject(viewModel: AlbumListViewModel)
    fun inject(albumViewModel: AlbumViewModel)

    interface Injectable {
        fun inject(appComponent: AppComponent)

    }
}
