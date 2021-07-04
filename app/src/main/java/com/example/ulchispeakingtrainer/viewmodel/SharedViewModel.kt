package com.example.ulchispeakingtrainer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ulchispeakingtrainer.data.*
import com.example.ulchispeakingtrainer.repository.UlchiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel (application: Application): AndroidViewModel(application) {
    private val dao = UlchiDatabase.getDatabase(application).ThemeDao()
    private val repository: UlchiRepository

    val allThemes: LiveData<List<Theme>>
    val allTexts: LiveData<List<UlchiText>>
    val allNewPhrases: LiveData<List<NewPhrase>>
    val emptyData: MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkIfEmpty(phrases: List<NewPhrase>) {
        emptyData.value = phrases.isEmpty()
    }

    init {
        repository = UlchiRepository(dao)
        allThemes = repository.getAllThemes()
        allTexts = repository.getAllTexts
        allNewPhrases = repository.getAllNewPhrases
    }

    fun getAllPhrasesByTheme(theme: String): LiveData<List<UlchiPhrase>> =
        repository.getPhrasesByTheme(theme)


    fun insertPhrase(newPhrase: NewPhrase) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNewPhrase(newPhrase)
        }
    }

    fun deletePhrases(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePhrase(id)
        }
    }

    fun update(phrase: NewPhrase) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(phrase)
        }
    }
}