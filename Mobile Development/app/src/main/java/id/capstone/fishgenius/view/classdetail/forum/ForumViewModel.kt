package id.capstone.fishgenius.view.classdetail.forum

import androidx.lifecycle.ViewModel
import id.capstone.fishgenius.data.FishGeniusRepository

class ForumViewModel(private val fishGeniusRepository: FishGeniusRepository): ViewModel() {
    fun getAllForum(classMap: String) = fishGeniusRepository.getAllForum(classMap)
}