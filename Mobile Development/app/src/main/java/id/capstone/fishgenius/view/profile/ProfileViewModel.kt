package id.capstone.fishgenius.view.profile

import androidx.lifecycle.ViewModel
import id.capstone.fishgenius.data.FishGeniusRepository

class ProfileViewModel(private val fishGeniusRepository:  FishGeniusRepository): ViewModel() {
    fun getProfileUser(profileMap: HashMap<String, String>) = fishGeniusRepository.getProfileUser(profileMap)
}