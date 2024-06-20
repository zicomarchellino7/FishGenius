package id.capstone.fishgenius.view.home

import androidx.lifecycle.ViewModel
import id.capstone.fishgenius.data.FishGeniusRepository

class HomeViewModel(private val fishGeniusRepository:  FishGeniusRepository): ViewModel() {
    fun getHomeData(homeMap: HashMap<String, String>) = fishGeniusRepository.getHomeData(homeMap)
}