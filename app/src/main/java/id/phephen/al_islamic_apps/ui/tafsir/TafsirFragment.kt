package id.phephen.al_islamic_apps.ui.tafsir

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.phephen.al_islamic_apps.R
import id.phephen.al_islamic_apps.databinding.FragmentTafsirBinding
import id.phephen.al_islamic_apps.helper.Helper
import id.phephen.al_islamic_apps.helper.Resource
import id.phephen.al_islamic_apps.network.response.TafsirResponse
import id.phephen.al_islamic_apps.ui.detail.DetailViewModel

class TafsirFragment : Fragment() {
    private lateinit var binding: FragmentTafsirBinding
    private val detailViewModel by lazy { ViewModelProvider(requireActivity()).get(DetailViewModel::class.java) }
    private var isPlaying = false
    private val mediaHelper by lazy { Helper(requireContext()) }
    private var mediaUrl = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTafsirBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupListener()
    }

    private fun setupListener() {
        binding.btnAudio.setOnClickListener {
            if (!isPlaying) {
                isPlaying = mediaHelper.playAudio(mediaUrl)
            } else {
                isPlaying = mediaHelper.stopAudio()
            }
            setupBtnAudio(isPlaying)
        }
    }

    private fun setupObserver() {
        detailViewModel.tafsirResponse.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Loading -> {
                    binding.rolling.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.rolling.visibility = View.GONE
                    setupView(it.data!!.data)
                    mediaUrl = it.data?.data?.audio!!.secondary[0].toString()
                }
                is Resource.Error -> {
                    binding.rolling.visibility = View.GONE
                }
            }
        })
    }

    private fun setupView(data: TafsirResponse.Data) {
        binding.tvArab.text = data.text.arab
        binding.tvTerjemah.text = data.translation.id
        binding.tvTafsirShort.text = data.tafsir.id.short
        binding.tvTafsirLong.text = data.tafsir.id.long
        binding.tvTitle.text = data.surah.name.transliteration.id+ " : "+data.number.inSurah.toString()
    }

    fun setupBtnAudio(isPlaying: Boolean) {
        if (isPlaying) {
            binding.btnAudio.setBackgroundDrawable(requireActivity().getDrawable(R.drawable.ic_stop_button))
        } else {
            binding.btnAudio.setBackgroundDrawable(requireActivity().getDrawable(R.drawable.ic_play_button))
        }
    }

}