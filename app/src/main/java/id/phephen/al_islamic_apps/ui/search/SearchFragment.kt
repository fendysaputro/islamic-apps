package id.phephen.al_islamic_apps.ui.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.phephen.al_islamic_apps.R
import id.phephen.al_islamic_apps.databinding.FragmentSearchBinding
import id.phephen.al_islamic_apps.helper.Resource
import id.phephen.al_islamic_apps.network.response.ListSurahResponse
import id.phephen.al_islamic_apps.ui.detail.DetailActivity
import id.phephen.al_islamic_apps.ui.home.HomeViewModel
import id.phephen.al_islamic_apps.ui.search.adapter.SearchAdapter

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val homeViewModel by lazy { ViewModelProvider(requireActivity()).get(HomeViewModel::class.java) }
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupRV()
        setupListener()
    }

    private fun setupListener() {
        binding.etSearch.doAfterTextChanged {
            searchAdapter.filter.filter(it.toString())
        }
        binding.refreshList.setOnRefreshListener {
            homeViewModel.fetchListSurah()
        }
    }

    private fun setupObserver() {
        homeViewModel.listSurahResponse.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Loading -> Log.d("_result2", "Loading")
                is Resource.Success -> {
                    Log.d("_result2", it.data.toString())
                    searchAdapter.setData(it.data!!.data)
                }
                is Resource.Error -> Log.d("_result2", it.message.toString())
            }
        })
    }

    private fun setupRV() {
        searchAdapter = SearchAdapter(arrayListOf(), object: SearchAdapter.OnAdapterListener {
            override fun onClick(result: ListSurahResponse.DetailSurah) {
                startActivity(Intent(requireContext(), DetailActivity::class.java)
                    .putExtra("numberSurah", result.number.toString()))
            }
        })
        binding.rvListSearch.adapter = searchAdapter
    }

}