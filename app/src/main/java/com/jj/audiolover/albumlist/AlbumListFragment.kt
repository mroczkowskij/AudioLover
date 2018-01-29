package com.jj.audiolover.albumlist

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.SearchView
import com.jj.audiolover.R
import com.jj.audiolover.databinding.FragmentAlbumListBinding
import com.jj.audiolover.di.ViewModelFactory
import com.jj.audiolover.viemodel.AlbumListViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_album_list.*
import kotlinx.android.synthetic.main.fragment_album_list.view.*
import javax.inject.Inject

/**
 * Created by JJ on 27.01.2018.
 */
class AlbumListFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentAlbumListBinding>(inflater!!, R.layout.fragment_album_list, container, false)
        binding.model = albumViewModel()
        (activity as AppCompatActivity).setSupportActionBar(binding.root.toolbar)
        return binding.root
    }

    private fun albumViewModel() =
            ViewModelProviders.of(activity!!, viewModelFactory).get(AlbumListViewModel::class.java)

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView
        
        searchView.setIconifiedByDefault(false)
        searchView.setOnQueryTextListener(albumViewModel())

        super.onCreateOptionsMenu(menu, inflater)
    }
}