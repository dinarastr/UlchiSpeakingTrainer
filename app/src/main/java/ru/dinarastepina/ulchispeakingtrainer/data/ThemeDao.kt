package ru.dinarastepina.ulchispeakingtrainer.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ThemeDao {

    @Query("select * from ulchi_themes order by id asc")
    fun getAllThemes(): LiveData<List<Theme>>

    @Query("select * from ulchi_phrases where theme like :theme order by id asc")
    fun getAllPhrasesByTheme(theme: String): LiveData<List<UlchiPhrase>>

    @Query("select * from new_phrases order by id asc")
    fun getAllNewPhrases(): LiveData<List<NewPhrase>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewPhrase(newphrase: NewPhrase)

   @Query("select * from ulchi_texts")
   fun getAllTexts(): LiveData<List<UlchiText>>

    @Query("delete from new_phrases where id == :id")
    suspend fun deletePhrase(id: Int)

    @Update
    suspend fun update(phrase: NewPhrase)
}