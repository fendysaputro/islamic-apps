package id.phephen.al_islamic_apps

import android.app.Application
import id.phephen.al_islamic_apps.network.AlquranRepository
import id.phephen.al_islamic_apps.network.ApiService
import id.phephen.al_islamic_apps.network.EndPoint
import id.phephen.al_islamic_apps.ui.detail.DetailViewModelFactory
import id.phephen.al_islamic_apps.ui.home.HomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Created by Phephen on 02/06/2022.
 */
class AlquranApp: Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@AlquranApp))
        bind<EndPoint>() with singleton { ApiService.getClient() }
        bind() from provider { AlquranRepository(instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }
        bind() from provider { DetailViewModelFactory(instance()) }
    }

}