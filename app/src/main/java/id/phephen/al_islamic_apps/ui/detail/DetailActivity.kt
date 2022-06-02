package id.phephen.al_islamic_apps.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import id.phephen.al_islamic_apps.R
import id.phephen.al_islamic_apps.databinding.ActivityDetailBinding
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class DetailActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private val detailFactory: DetailViewModelFactory by instance()
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, detailFactory).get(DetailViewModel::class.java)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}