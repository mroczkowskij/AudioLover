package com.jj.audiolover.data

/**
 * Created by JJ on 27.01.2018.
 */
data class AlbumDetails(
        val title: String,
        val tracklist: List<Track>
)

data class Track (val title: String)
