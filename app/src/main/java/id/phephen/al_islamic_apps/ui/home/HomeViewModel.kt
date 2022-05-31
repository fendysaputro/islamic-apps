package id.phephen.al_islamic_apps.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.phephen.al_islamic_apps.helper.Resource
import id.phephen.al_islamic_apps.network.AlquranRepository
import id.phephen.al_islamic_apps.network.response.ListSurahResponse
import kotlinx.coroutines.launch

/**
 * Created by Phephen on 31/05/2022.
 */
class HomeViewModel(private val repository: AlquranRepository): ViewModel() {

    val listSurahResponse: MutableLiveData<Resource<ListSurahResponse>> = MutableLiveData()

    fun fetchListSurah() = viewModelScope.launch {
        listSurahResponse.value = Resource.Loading()
        try {
            val response = repository.fetchListSurah()
            listSurahResponse.value = Resource.Success(response.body()!!)
        } catch (e: Exception) {
            listSurahResponse.value = Resource.Error(e.message.toString())
        }
    }

}