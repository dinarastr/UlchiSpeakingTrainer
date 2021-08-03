package ru.dinarastepina.ulchispeakingtrainer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.dinarastepina.ulchispeakingtrainer.data.*
import ru.dinarastepina.ulchispeakingtrainer.repository.UlchiRepository

class SharedViewModel (application: Application): AndroidViewModel(application) {
    private val dao = UlchiDatabase.getDatabase(application).ThemeDao()
    private val repository: UlchiRepository = UlchiRepository(dao)

    val allThemes: LiveData<List<Theme>> = repository.getAllThemes()
    val allTexts: LiveData<List<UlchiText>> = repository.getAllTexts
    val allNewPhrases: LiveData<List<NewPhrase>> = repository.getAllNewPhrases
    val emptyData: MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkIfEmpty(phrases: List<NewPhrase>) {
        emptyData.value = phrases.isEmpty()
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