package ru.dinarastepina.ulchispeakingtrainer.ui

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_audio.*
import ru.dinarastepina.ulchispeakingtrainer.R


class AudioFragment : Fragment() {

    private val args by navArgs<AudioFragmentArgs>()
    private var mp: MediaPlayer? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_audio, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        audio_text.text = args.currentText.ulchitext
        russian_text.text = args.currentText.russiantext
        forward_btn.setOnClickListener {
            if (mp == null) playSound(args.currentText.filename ?: "")
            seekbar.max = mp?.duration ?: 0
            val handler = Handler()
            handler.postDelayed(object : Runnable {
                override fun run() {
                    if (seekbar != null) {
                        seekbar.progress = mp?.currentPosition ?: 0
                        handler.postDelayed(this, 500)
                    }
                }
            }, 0)
            mp?.start()
        }
        pause_btn.setOnClickListener {
            if (mp != null) {
                mp?.pause()
            }
        }
        stop_btn.setOnClickListener {
            mp?.stop()
            mp?.reset()
            mp?.release()
            mp = null
        }
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mp?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }

    override fun onStop() {
        super.onStop()
        mp?.stop()
        mp?.release()
    }


    private fun playSound(soundName: String) {
        mp = MediaPlayer.create(context, Uri.parse("android.resource://ru.dinarastepina.ulchispeakingtrainer/raw/" + soundName))
    }
}
