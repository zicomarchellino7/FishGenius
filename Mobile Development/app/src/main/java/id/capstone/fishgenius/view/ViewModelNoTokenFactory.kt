package id.capstone.fishgenius.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.capstone.fishgenius.data.FishGeniusRepository
import id.capstone.fishgenius.data.di.Injection
import id.capstone.fishgenius.view.login.LoginViewModel
import id.capstone.fishgenius.view.register.RegisterViewModel

class ViewModelNoTokenFactory private constructor(private val fishGeniusRepository: FishGeniusRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(fishGeniusRepository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(fishGeniusRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
 
    companion object {
        @Volatile
        private var instance: ViewModelNoTokenFactory? = null
        fun getInstance(context: Context): ViewModelNoTokenFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelNoTokenFactory(Injection.provideRepository(context, ""))
            }.also { instance = it }
    }
}