package com.jj.audiolover.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by JJ on 27.01.2018.
 */
interface DiscogsWebInterface {
    //probably should be wrapped as a data layer

    //    @GET("https://api.discogs.com/database/search?release_title={search}&format=album&key=gjxIICNVseJgHDJNituV&secret=GnjVaDWRncXgonbaaVPLybSIefnskOTe")
    @GET("https://api.discogs.com/database/search")
    fun listAlbum(@Query("release_title") search: String,
                  @Query("format") format : String = "album"): Call<AlbumResult>

    @GET("https://api.discogs.com/masters/{id}")
    fun albumDetails(@Path("id") id: Int): Call<AlbumDetails>
}