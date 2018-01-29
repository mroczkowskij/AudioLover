package com.jj.audiolover.albumDetails

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jj.audiolover.R
import com.jj.audiolover.viemodel.AlbumListViewModel
import com.jj.audiolover.databinding.FragmentAlbumDetailsBinding

/**
 * Created by JJ on 27.01.2018.
 */
class AlbumDetailsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentAlbumDetailsBinding>(inflater!!, R.layout.fragment_album_details, container, false)
        binding.model = ViewModelProviders.of(activity).get(AlbumListViewModel::class.java)
        binding.executePendingBindings()
        return binding.root
    }
}