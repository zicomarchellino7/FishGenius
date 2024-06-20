package id.capstone.fishgenius.view.login

import androidx.lifecycle.ViewModel
import id.capstone.fishgenius.data.FishGeniusRepository

class LoginViewModel(private val fishgeniusRepository: FishGeniusRepository): ViewModel() {
    fun loginUser(loginMap: HashMap<String, String>) = fishgeniusRepository.loginUser(loginMap)
}