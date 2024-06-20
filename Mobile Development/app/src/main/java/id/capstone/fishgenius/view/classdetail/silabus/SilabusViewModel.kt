package id.capstone.fishgenius.view.classdetail.silabus

import androidx.lifecycle.ViewModel
import id.capstone.fishgenius.data.FishGeniusRepository

class SilabusViewModel(private val tanaminRepository: FishGeniusRepository): ViewModel() {
    fun getAllModule(classMap: String) = tanaminRepository.getAllModule(classMap)
}