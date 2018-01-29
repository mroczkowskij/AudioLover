package com.jj.audiolover.di.features

import com.jj.audiolover.albumlist.AlbumListFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by JJ on 27.01.2018.
 */
@Subcomponent
interface AlbumListFragmentComponent : AndroidInjector<AlbumListFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<AlbumListFragment>()
}