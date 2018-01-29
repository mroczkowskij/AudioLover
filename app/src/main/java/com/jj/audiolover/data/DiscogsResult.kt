package com.jj.audiolover.data

/**
 * Created by JJ on 28.01.2018.
 */
data class AlbumResult (val pagination: Pagination, val results: List<Album>)
data class Pagination (val per_page: Int, val items: Int, val pages: Int, val urls: PaginationNavigation)

data class PaginationNavigation(val last: String, val next: String)