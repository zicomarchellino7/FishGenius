package id.capstone.fishgenius.view.classes

import androidx.lifecycle.ViewModel
import id.capstone.fishgenius.data.FishGeniusRepository

class ClassesViewModel(private val fishGeniusRepository:  FishGeniusRepository): ViewModel() {
    fun getAllClass(classMap: HashMap<String,String>) = fishGeniusRepository.getAllClass(classMap)
    fun searchWord(word: String) = fishGeniusRepository.getSearchClass(word)
}