package id.phephen.al_islamic_apps.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.phephen.al_islamic_apps.R
import id.phephen.al_islamic_apps.databinding.FragmentDetailBinding
import id.phephen.al_islamic_apps.helper.Resource
import id.phephen.al_islamic_apps.network.response.DetailSurahResponse
import id.phephen.al_islamic_apps.ui.detail.adapter.ListAyahAdapter

class DetailFragment : Fragment() {
    private val noSurah by lazy { requireActivity().intent.getStringExtra("numberSurah") }
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(DetailViewModel::class.java) }
    private lateinit var binding: FragmentDetailBinding
    private lateinit var listAyahAdapter: ListAyahAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchDetailSurah(noSurah.toString())
        setupObserver()
        setupListener()
    }

    private fun setupListener() {

    }

    private fun setupObserver() {
        viewModel.detailResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.rolling.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.rolling.visibility = View.GONE
                    setupView(it.data)
                    setupRV(it.data!!.data.verses)
                }
                is Resource.Error -> {
                    binding.rolling.visibility = View.GONE
                }
            }
        })
    }

    private fun setupRV(verses: List<DetailSurahResponse.DataSurah.VersesSurah>) {
        listAyahAdapter = ListAyahAdapter( verses, object : ListAyahAdapter.OnAdapterListener {
            override fun onClick(result: DetailSurahResponse.DataSurah.VersesSurah) {
                viewModel.fetchTafsir(
                    noSurah.toString(),
                    result.number.inSurah.toString())
                findNavController().navigate(R.id.action_detailFragment_to_tafsirFragment)
            }
        })
        binding.rvListAyat.adapter = listAyahAdapter
    }

    private fun setupView(data: DetailSurahResponse?) {
        binding.tvNumberSurah.text = data!!.data.number.toString()
        binding.tvNameSurah.text = data!!.data.name.long + " | " + data!!.data.name.transliteration.id
        binding.tvInfoSurah.text = data!!.data.numberOfVerses.toString()+ " | "+ data!!.data.revelation.id
    }

}