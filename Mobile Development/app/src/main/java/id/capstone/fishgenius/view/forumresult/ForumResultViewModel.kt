package id.capstone.fishgenius.view.forumresult

import androidx.lifecycle.ViewModel
import id.capstone.fishgenius.data.FishGeniusRepository

class ForumResultViewModel(private val fishGeniusRepository: FishGeniusRepository): ViewModel() {
    fun getAllMessage(forumId: String) = fishGeniusRepository.getAllMessage(forumId)
    fun sendMessage(messageData: HashMap<String, String>) = fishGeniusRepository.sendMessage(messageData)
}