package com.jj.audiolover.albumlist

import android.view.View
import com.jj.audiolover.data.Album

interface OnAlbumClickListener {
    fun selectAlbum(album: Album, view: View)
}