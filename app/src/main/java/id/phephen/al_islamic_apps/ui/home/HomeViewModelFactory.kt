package id.phephen.al_islamic_apps.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.phephen.al_islamic_apps.network.AlquranRepository

/**
 * Created by Phephen on 31/05/2022.
 */
class HomeViewModelFactory(private val repository: AlquranRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }

}