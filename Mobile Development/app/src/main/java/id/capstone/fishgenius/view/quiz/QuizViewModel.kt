package id.capstone.fishgenius.view.quiz

import androidx.lifecycle.ViewModel
import id.capstone.fishgenius.data.FishGeniusRepository

class QuizViewModel(private val fishGeniusRepository:  FishGeniusRepository): ViewModel(){
    fun getQuiz(moduleData:HashMap<String,String>)=fishGeniusRepository.getQuiz(moduleData)
    fun sendAnswer(listAnswer: List<String>, moduleData:HashMap<String,String>)=fishGeniusRepository.sendAnswer(listAnswer, moduleData)
}