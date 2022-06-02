package id.phephen.al_islamic_apps.ui.detail

import TafsirResponse
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.phephen.al_islamic_apps.helper.Resource
import id.phephen.al_islamic_apps.network.AlquranRepository
import id.phephen.al_islamic_apps.network.response.DetailSurahResponse
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Created by Phephen on 31/05/2022.
 */
class DetailViewModel(private val repository: AlquranRepository): ViewModel() {

    val detailResponse: MutableLiveData<Resource<DetailSurahResponse>> = MutableLiveData()
    val tafsirResponse: MutableLiveData<Resource<TafsirResponse>> = MutableLiveData()

    fun fetchDetailSurah(
        surah: String
    ) = viewModelScope.launch {
        detailResponse.value = Resource.Loading()
        Log.d("_result3", "Loading!!")
        try {
            val response = repository.fetchDetailSurah(surah)
            detailResponse.value = Resource.Success(response.body()!!)
            Log.d("_result3", response.body().toString() )
        } catch (e: Exception) {
            detailResponse.value = Resource.Error(e.message.toString())
            Log.d("_result3", e.message.toString() )
        }
    }

    fun fetchTafsir(
        surah: String,
        ayat: String
    ) = viewModelScope.launch {
        tafsirResponse.value = Resource.Loading()
        try {
            val response = repository.fetchTafsir(surah, ayat)
            tafsirResponse.value = Resource.Success(response.body()!!)
        } catch (e: Exception) {
            tafsirResponse.value = Resource.Error(e.message.toString())
        }
    }

}