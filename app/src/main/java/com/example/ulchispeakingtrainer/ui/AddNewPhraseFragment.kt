package com.example.ulchispeakingtrainer.ui

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.ulchispeakingtrainer.R
import com.example.ulchispeakingtrainer.data.NewPhrase
import com.example.ulchispeakingtrainer.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_add_new_phrase.*
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class AddNewPhraseFragment : Fragment() {
    private val vm: SharedViewModel by viewModels()
    private var mr: MediaRecorder? = null
    var path = ""
    var isRecording = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_new_phrase, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val format: SimpleDateFormat = SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.US)
        val date = Date()
        path = requireContext().filesDir.absolutePath + "Recording_"+format.format(date) + ".3gp"
        start_btn.setOnClickListener {
            if (isRecording && mr != null) {
                start_btn.setImageResource(R.drawable.ic_baseline_mic_24)
                timer.stop()
                mr?.stop()
                mr?.reset()
                mr?.release()
                mr = null
                isRecording = false
                Toast.makeText(requireContext(), "Запись остановлена. Прослушайте фразу и сохраните ее.", Toast.LENGTH_LONG).show()
                listen(path)
            } else {
                if (ulchi_phrase.text.isNotEmpty() && russian_phrase.text.isNotEmpty()) {
                    if (recordingPermitted()) {
                        start_btn.setImageResource(R.drawable.ic_baseline_stop_circle_24)
                        timer.base = SystemClock.elapsedRealtime()
                        timer.start()
                        mr = MediaRecorder()
                        mr?.setAudioSource(MediaRecorder.AudioSource.MIC)
                        mr?.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                        mr?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                        mr?.setOutputFile(path)
                        mr?.prepare()
                        mr?.start()
                        Toast.makeText(requireContext(), "Запись началась!", Toast.LENGTH_SHORT)
                            .show()
                        isRecording = true
                    } else {
                        Toast.makeText(requireContext(), "Вы не разрешили запись звука. Чтобы записать аудио, зайдите в настройки разрешений вашего телефона и разрешите Ульчскому разговорнику использовать микрофон.", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Сначала напишите фразы!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun listen(path: String) {
        val mp = MediaPlayer()
        mp.setDataSource(path)
        mp.isLooping = false
        try {
            mp.prepare()
        } catch (e: Exception) {
            Log.i("Ulchi", e.message.toString())
        }
        mp.start()
        mp.setOnCompletionListener {
                it -> if (it.isPlaying && it != null) it.stop()
            it.reset()
            it.release()
            showAddDialog()
        }
    }

    private fun recordingPermitted(): Boolean {
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) return true
        else requestPermissions(arrayOf(android.Manifest.permission.RECORD_AUDIO), 111)
        return false
    }

    private fun showAddDialog() {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.apply {
            setTitle("Сохранить эту фразу?")
            setCancelable(true)
            setPositiveButton("Да") {
                    _, _ ->  addNewPhrase()
            }
            setNegativeButton("Нет") {
                    _, _ ->  deleteFile()
            }
        }
        dialog.create()
        dialog.show()
    }

    private fun deleteFile() {
        val file = File(path)
        file.delete()
    }

    private fun addNewPhrase() {
        vm.insertPhrase(NewPhrase(0, russian_phrase.text.toString(), ulchi_phrase.text.toString(), path))
        Toast.makeText(requireContext(), "Новая фраза добавлена!", Toast.LENGTH_SHORT).show()
        start_btn.findNavController().navigateUp()
    }
}