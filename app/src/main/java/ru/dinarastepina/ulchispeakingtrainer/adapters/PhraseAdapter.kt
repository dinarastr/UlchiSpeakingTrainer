package ru.dinarastepina.ulchispeakingtrainer.adapters

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.theme_row.view.*
import ru.dinarastepina.ulchispeakingtrainer.R
import ru.dinarastepina.ulchispeakingtrainer.data.UlchiPhrase
import ru.dinarastepina.ulchispeakingtrainer.playSoundFromAssets

class PhraseAdapter: RecyclerView.Adapter<PhraseAdapter.PhraseViewHolder>() {
    var themeList = emptyList<UlchiPhrase>()
    private var mMediaPlayer: MediaPlayer? = null

    class PhraseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhraseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.phrase_row, parent, false)
        return PhraseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return themeList.size
    }

    override fun onBindViewHolder(holder: PhraseViewHolder, position: Int) {
        holder.itemView.ulchi_theme.text = themeList[position].ulchiphrase
        holder.itemView.russian_theme.text = themeList[position].russianphrase
        holder.itemView.theme_image.setImageResource(R.drawable.ic_baseline_audiotrack_24)
        holder.itemView.theme_background.setOnClickListener {
            playSound(holder.itemView.context, themeList[position].filename ?: "")
        }
    }

    private fun playSound(context: Context, soundName: String) {
        mMediaPlayer?.stop()
        mMediaPlayer?.release()
        mMediaPlayer = context.playSoundFromAssets(soundName)
    }

    fun setData(theme: List<UlchiPhrase>) {
        this.themeList = theme
        notifyDataSetChanged()
    }
}