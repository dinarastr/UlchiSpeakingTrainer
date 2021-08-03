package ru.dinarastepina.ulchispeakingtrainer.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "ulchi_themes")
@Parcelize
data class Theme(
    @PrimaryKey
    var id: Int,
    var ulchi: String?,
    var russian: String?,
    var picture: String?
): Parcelable

@Entity(tableName = "ulchi_phrases")
data class UlchiPhrase(
    @PrimaryKey
    var id: Int,
    var ulchiphrase: String?,
    var russianphrase: String?,
    var filename: String?,
    var theme: String?
)

@Parcelize
@Entity(tableName = "new_phrases")
data class NewPhrase(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var russian: String?,
    var ulchi: String?,
    var voice: String?
): Parcelable

@Parcelize
@Entity(tableName = "ulchi_texts")
data class UlchiText(
    @PrimaryKey
    var id: Int,
    var russiantext: String?,
    var ulchitext: String?,
    var filename: String?
): Parcelable
