package ru.dinarastepina.ulchispeakingtrainer

import android.content.Context
import android.media.MediaPlayer

fun Context.playSoundFromAssets(soundName: String): MediaPlayer {
    val fd = assets.openFd("sounds/$soundName.mp3")
    return MediaPlayer().apply {
        setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        prepare()
        isLooping = false
        start()
    }
}