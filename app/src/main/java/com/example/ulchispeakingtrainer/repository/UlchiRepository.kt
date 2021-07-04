package com.example.ulchispeakingtrainer.repository

import androidx.lifecycle.LiveData
import com.example.ulchispeakingtrainer.data.*

class UlchiRepository(private val ulchiDao: ThemeDao) {

    fun getAllThemes(): LiveData<List<Theme>> = ulchiDao.getAllThemes()

    fun getPhrasesByTheme(theme: String): LiveData<List<UlchiPhrase>> {
        return ulchiDao.getAllPhrasesByTheme(theme)
    }

    val getAllNewPhrases: LiveData<List<NewPhrase>> = ulchiDao.getAllNewPhrases()

    val getAllTexts: LiveData<List<UlchiText>> = ulchiDao.getAllTexts()

    suspend fun addNewPhrase(phrase: NewPhrase){
        ulchiDao.insertNewPhrase(phrase)
    }

    suspend fun deletePhrase(id: Int) {
        ulchiDao.deletePhrase(id)
    }

    suspend fun update(phrase: NewPhrase) {
        ulchiDao.update(phrase)
    }
}