package com.jj.audiolover.viemodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.view.View
import android.widget.SearchView
import com.jj.audiolover.BR
import com.jj.audiolover.R
import com.jj.audiolover.albumDetails.AlbumDetailsFragment
import com.jj.audiolover.di.AppComponent
import me.tatarka.bindingcollectionadapter2.ItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import com.jj.audiolover.albumlist.OnAlbumClickListener
import com.jj.audiolover.data.*
import com.jj.audiolover.view.ScreenManager


/**
 * Created by JJ on 27.01.2018.
 */
class AlbumListViewModel : ViewModel(), AppComponent.Injectable, SearchView.OnQueryTextListener, OnAlbumClickListener {
    @Inject lateinit var discogsWebInterface: DiscogsWebInterface
    @Inject lateinit var screenManager: ScreenManager

    val album = MutableLiveData<Album>()
    val albums = ObservableArrayList<Album>()
    val albumDetails = MutableLiveData<AlbumDetails>()
    val albumDetailsTracks = ObservableArrayList<Track>()

    val albumBinding: ItemBinding<Album> = ItemBinding
            .of<Album>(BR.album, R.layout.album_list_item)
            .bindExtra(BR.listener, this)

    val trackBinding: ItemBinding<Track> = ItemBinding.of(BR.track, R.layout.album_details_track)

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    /**
     * Handle search
     */
    override fun onQueryTextSubmit(searchedText: String?): Boolean {
        if (searchedText != null){
            albums.clear()
            discogsWebInterface.listAlbum(searchedText).enqueue(object : Callback<AlbumResult> {
                override fun onFailure(call: Call<AlbumResult>?, t: Throwable?) {
                    Timber.d("Cos poszlo nie tak", t)
                }

                override fun onResponse(call: Call<AlbumResult>?, response: Response<AlbumResult>?) {
                    response?.body()?.results?.let { albumResult -> albums.addAll(albumResult) }
//                    response?.body()?.pagination?.urls?.next  TODO handle pagination
                }
            })
        }
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean = false

    override fun selectAlbum(album: Album, view: View) {
        albumDetails.postValue(null)
        albumDetailsTracks.clear()

        discogsWebInterface.albumDetails(album.id).enqueue(object : Callback<AlbumDetails> {
            override fun onFailure(call: Call<AlbumDetails>?, t: Throwable?) {
                Timber.d("Cos poszlo nie tak", t)
            }

            override fun onResponse(call: Call<AlbumDetails>?, response: Response<AlbumDetails>?) {
                response?.body()?.let {
                    albumResult ->
                    albumDetails.postValue(albumResult)
                    albumDetailsTracks.addAll(albumResult.tracklist)
                }
            }
        })

        this.album.postValue(album)
        screenManager.showFragmentTransition(view.findViewById(R.id.thumb), AlbumDetailsFragment())
    }
}