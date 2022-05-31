package id.phephen.al_islamic_apps.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import id.phephen.al_islamic_apps.R
import id.phephen.al_islamic_apps.databinding.ActivityMainBinding
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {
    override val kodein by closestKodein()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val homeFactory: HomeViewModelFactory by instance()
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewModel()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, homeFactory).get(HomeViewModel::class.java)
        viewModel.fetchListSurah()
    }
}