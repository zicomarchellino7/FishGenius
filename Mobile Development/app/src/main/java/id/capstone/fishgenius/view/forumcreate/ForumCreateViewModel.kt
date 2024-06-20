package id.capstone.fishgenius.view.forumcreate

import androidx.lifecycle.ViewModel
import id.capstone.fishgenius.data.FishGeniusRepository

class ForumCreateViewModel(private val fishGeniusRepository: FishGeniusRepository): ViewModel() {
    fun createForum(classMap: HashMap<String, String>) = fishGeniusRepository.createForum(classMap)
}