package com.zebas2.inboxapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.zebas2.inboxapp.R
import com.zebas2.inboxapp.data.model.Post
import com.zebas2.inboxapp.databinding.ActivityMainBinding
import com.zebas2.inboxapp.domain.usecase.GetMessagesUseCase
import com.zebas2.inboxapp.presentation.viewmodel.MessagesViewModel
import com.zebas2.inboxapp.presentation.viewmodel.MessagesViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /*@Inject
    lateinit var getMessagesUseCase: GetMessagesUseCase*/

    @Inject
    lateinit var viewModelFactory: MessagesViewModelFactory

    lateinit var viewModel: MessagesViewModel

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavigation()

        viewModel = ViewModelProvider(this, viewModelFactory).get(MessagesViewModel::class.java)
        /*GlobalScope.launch {
            val list = getMessagesUseCase.execute()
            Log.d(TAG, "onCreate: ${list.toString()}")
        }*/
    }

    private fun setUpNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        /*val appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
        supportActionBar?.apply {
            setupActionBarWithNavController(navController, appBarConfiguration)
        }*/
    }
}