package id.capstone.fishgenius.view.classmodule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.capstone.fishgenius.data.FishGeniusRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ClassModuleViewModel(private val fishGeniusRepository: FishGeniusRepository): ViewModel(){

    private val _isProgressPictureUploaded = MutableLiveData<Boolean>()
    val isProgressPictureUploaded: LiveData<Boolean> = _isProgressPictureUploaded

    fun uploadProgress(progressPictureMultipart: MultipartBody.Part, userId: RequestBody,classId:RequestBody)=fishGeniusRepository.uploadProgress(progressPictureMultipart,userId,classId)
    fun getDetailModule(moduleData:HashMap<String,String>)=fishGeniusRepository.getDetailModule(moduleData)

    fun setProgressPictureUploadedStatus(boolean: Boolean){
        _isProgressPictureUploaded.value=boolean
    }
}