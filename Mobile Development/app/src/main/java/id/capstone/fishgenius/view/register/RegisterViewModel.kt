package id.capstone.fishgenius.view.register

import androidx.lifecycle.ViewModel
import id.capstone.fishgenius.data.FishGeniusRepository

class RegisterViewModel(private val fishGeniusRepository:FishGeniusRepository): ViewModel() {
    fun registerUser(map: HashMap<String, String>) = fishGeniusRepository.registerUser(map)
}