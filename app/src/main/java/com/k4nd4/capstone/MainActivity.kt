package com.k4nd4.capstone

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.k4nd4.capstone.databinding.ActivityMainBinding
import com.k4nd4.capstone.di.appInjectModule
import com.k4nd4.capstone.di.useCaseModule
import com.k4nd4.capstone.di.viewModelModule
import com.k4nd4.capstone.screen.HomeFragment
import com.k4nd4.capstone.viewmodel.MainViewModel
import com.k4nd4.core.data.Resource
import com.k4nd4.core.di.databaseModule
import com.k4nd4.core.di.networkModule
import com.k4nd4.core.di.repositoryModule
import com.k4nd4.core.presentation.viewpageradapter.SectionPageAdapter
import com.k4nd4.core.utils.showToast
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var splitInstallManager: SplitInstallManager

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.home,
            R.string.favorite
        )
    }

    private val viewModel: MainViewModel by viewModel()

    init {
        if(GlobalContext.getOrNull() == null){
            GlobalContext.startKoin {
                androidLogger(Level.NONE)
                androidContext(this@MainActivity)
                modules(
                    listOf(
                        networkModule,
                        databaseModule,
                        repositoryModule,
                        viewModelModule,
                        useCaseModule,
                        appInjectModule
                    )
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        splitInstallManager = SplitInstallManagerFactory.create(this)

        if (splitInstallManager.installedModules.contains("favorite")) {
            val favoriteFragmentClass = Class.forName("com.k4nd4.favorite.FavoriteFragment")
            val favoriteFragment = favoriteFragmentClass.newInstance() as Fragment
            val fragmentList = listOf(HomeFragment(), favoriteFragment)
            setUpSectionPageAdapter(fragmentList)
        } else {
            setUpSectionPageAdapter()
            showSnackBar()
        }

        viewModel.getDataFromApi().observe(this) { resource ->
            when (resource) {
                is Resource.Empty -> showToast(getString(R.string.no_data_network_error), this)
                is Resource.Error -> showToast(getString(R.string.no_network_error), this)
                is Resource.Success -> {
                    showToast(getString(R.string.data_update_success), this)
                }
            }
        }

        supportActionBar?.elevation = 0f
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }

    private fun setUpSectionPageAdapter(
        fragmentList: List<Fragment> = listOf(HomeFragment())
    ) {
        val sectionPageAdapter = SectionPageAdapter(this)
        sectionPageAdapter.addFragment(fragmentList)
        binding.apply {
            pgBar.visibility = View.INVISIBLE
            viewPager.adapter = sectionPageAdapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }

    private fun showSnackBar() {
        val noModuleSnackBar = Snackbar.make(
            binding.root,
            getString(R.string.no_module_error),
            Snackbar.LENGTH_INDEFINITE
        )
        noModuleSnackBar.setAction("Add") { installFavoriteModule() }
        noModuleSnackBar.show()
    }

    private fun installFavoriteModule() {
        val module = "favorite"
        val request = SplitInstallRequest.newBuilder()
            .addModule(module)
            .build()
        splitInstallManager.startInstall(request)
            .addOnSuccessListener {
                try {
                    showToast("Loading Favorite Module", this)
                    val favoriteFragmentClass = Class.forName("com.k4nd4.favorite.FavoriteFragment")
                    val favoriteFragment = favoriteFragmentClass.newInstance() as Fragment
                    val fragmentList = listOf(HomeFragment(), favoriteFragment)
                    setUpSectionPageAdapter(fragmentList)
                } catch (e: Exception) {
                    showToast(getString(R.string.module_install_error), this)
                }
            }
            .addOnFailureListener {
                showToast(getString(R.string.module_install_error), this)
            }
    }
}
