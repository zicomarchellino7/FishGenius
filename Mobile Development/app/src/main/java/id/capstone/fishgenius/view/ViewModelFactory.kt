package id.capstone.fishgenius.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.capstone.fishgenius.data.FishGeniusRepository
import id.capstone.fishgenius.data.di.Injection
import id.capstone.fishgenius.view.classdetail.forum.ForumViewModel
import id.capstone.fishgenius.view.classdetail.silabus.SilabusViewModel
import id.capstone.fishgenius.view.classes.ClassesViewModel
import id.capstone.fishgenius.view.classmodule.ClassModuleViewModel
import id.capstone.fishgenius.view.forumcreate.ForumCreateViewModel
import id.capstone.fishgenius.view.forumresult.ForumResultViewModel
import id.capstone.fishgenius.view.home.HomeViewModel
import id.capstone.fishgenius.view.profile.ProfileViewModel
import id.capstone.fishgenius.view.profileedit.ProfileEditViewModel
import id.capstone.fishgenius.view.quiz.QuizViewModel

class ViewModelFactory private constructor(private val fishGeniusRepository: FishGeniusRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(fishGeniusRepository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                return ProfileViewModel(fishGeniusRepository) as T
            }
            modelClass.isAssignableFrom(ClassesViewModel::class.java) -> {
                return ClassesViewModel(fishGeniusRepository) as T
            }
            modelClass.isAssignableFrom(ProfileEditViewModel::class.java) -> {
                return ProfileEditViewModel(fishGeniusRepository) as T
            }
            modelClass.isAssignableFrom(SilabusViewModel::class.java) -> {
                return SilabusViewModel(fishGeniusRepository) as T
            }
            modelClass.isAssignableFrom(ForumViewModel::class.java) -> {
                return ForumViewModel(fishGeniusRepository) as T
            }
            modelClass.isAssignableFrom(ForumCreateViewModel::class.java) -> {
                return ForumCreateViewModel(fishGeniusRepository) as T
            }
            modelClass.isAssignableFrom(ForumResultViewModel::class.java) -> {
                return ForumResultViewModel(fishGeniusRepository) as T
            }
            modelClass.isAssignableFrom(ClassModuleViewModel::class.java)->{
                return ClassModuleViewModel(fishGeniusRepository) as T
            }
            modelClass.isAssignableFrom(QuizViewModel::class.java)->{
                return QuizViewModel(fishGeniusRepository) as T

            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
 
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context, token: String): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context, token))
            }.also { instance = it }
    }
}