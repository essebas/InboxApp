package com.zebas2.inboxapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController

import com.zebas2.inboxapp.R
import com.zebas2.inboxapp.data.model.Post
import com.zebas2.inboxapp.databinding.ActivityMainBinding

import com.zebas2.inboxapp.presentation.adapter.MessageAdapter
import com.zebas2.inboxapp.presentation.viewmodel.MessagesViewModel
import com.zebas2.inboxapp.presentation.viewmodel.MessagesViewModelFactory
import com.zebas2.inboxapp.presentation.viewmodel.UserViewModel
import com.zebas2.inboxapp.presentation.viewmodel.UserViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /*@Inject
    lateinit var getMessagesUseCase: GetMessagesUseCase*/

    /*@Inject
    lateinit var getUserDetailUseCase: GetUserDetailUseCase*/

    @Inject
    lateinit var messageViewModelFactory: MessagesViewModelFactory

    @Inject
    lateinit var userViewModelFactory: UserViewModelFactory

    @Inject
    lateinit var messageAdapter: MessageAdapter

    lateinit var messageViewModel: MessagesViewModel
    lateinit var userViewModel: UserViewModel

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavigation()

        messageViewModel =
            ViewModelProvider(this, messageViewModelFactory).get(MessagesViewModel::class.java)
        userViewModel =
            ViewModelProvider(this, userViewModelFactory).get(UserViewModel::class.java)

        /*GlobalScope.launch {
            val user = getUserDetailUseCase.execute(1)
            Log.d(TAG, "onCreate: ${user.toString()}")
        }*/
    }

    private fun setUpNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
        supportActionBar?.apply {
            setupActionBarWithNavController(navController, appBarConfiguration)
        }
    }

    fun setToolbar(toolbar: Toolbar) {
        if (toolbar != null) {
            setSupportActionBar(toolbar)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}