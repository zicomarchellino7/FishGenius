package id.capstone.fishgenius.view.splashscreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import id.capstone.fishgenius.data.local.datastore.LoginPreferences
import id.capstone.fishgenius.data.local.datastore.PreferencesViewModel
import id.capstone.fishgenius.data.local.datastore.PreferencesViewModelFactory
import id.capstone.fishgenius.databinding.ActivitySplashScreenBinding
import id.capstone.fishgenius.main.MainActivity
import id.capstone.fishgenius.view.login.LoginActivity
import id.capstone.fishgenius.view.onboarding.OnBoardingActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "userSession")
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var preferencesViewModel: PreferencesViewModel
    private var idUser: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        setupViewModel()
    }

    private fun setupViewModel(){
        val pref = LoginPreferences.getInstance(dataStore)
        preferencesViewModel = ViewModelProvider(this, PreferencesViewModelFactory(pref)).get(
            PreferencesViewModel::class.java
        )

        preferencesViewModel.getIDUser().observe(this){ id ->
            idUser = id
        }

        preferencesViewModel.getStatusOnBoard().observe(this){ statusOnBoard ->
            if(statusOnBoard){
                if(idUser != 0){
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, splashDuration)
                }else{
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, splashDuration)
                }
            }else{
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this, OnBoardingActivity::class.java)
                    startActivity(intent)
                    finish()
                }, splashDuration)
            }
        }
    }

    companion object {
        const val splashDuration: Long = 3000
    }
}