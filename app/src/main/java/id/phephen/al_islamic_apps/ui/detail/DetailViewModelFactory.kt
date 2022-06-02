package id.phephen.al_islamic_apps.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.phephen.al_islamic_apps.network.AlquranRepository

/**
 * Created by Phephen on 02/06/2022.
 */
class DetailViewModelFactory(private val repository: AlquranRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(repository) as T
    }


}