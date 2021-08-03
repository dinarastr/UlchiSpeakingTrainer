package ru.dinarastepina.ulchispeakingtrainer.adapters

import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.new_phrase.view.*
import kotlinx.android.synthetic.main.theme_row.view.*
import kotlinx.android.synthetic.main.theme_row.view.russian_theme
import kotlinx.android.synthetic.main.theme_row.view.theme_background
import kotlinx.android.synthetic.main.theme_row.view.theme_image
import kotlinx.android.synthetic.main.theme_row.view.ulchi_theme
import ru.dinarastepina.ulchispeakingtrainer.R
import ru.dinarastepina.ulchispeakingtrainer.data.NewPhrase
import ru.dinarastepina.ulchispeakingtrainer.ui.NewPhrasesFragmentDirections

class NewPhraseAdapter: RecyclerView.Adapter<NewPhraseAdapter.MyViewHolder>() {

    var themeList = emptyList<NewPhrase>()
    private var mp: MediaPlayer? = null
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        return themeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.ulchi_theme.text = themeList[position].ulchi
        holder.itemView.theme_image.setImageResource(R.drawable.ic_baseline_audiotrack_24)
        holder.itemView.russian_theme.text = themeList[position].russian
        holder.itemView.theme_background.setOnClickListener {
           playSound(themeList[position].voice ?: "")
        }

        holder.itemView.edit.setOnClickListener {
            val action = NewPhrasesFragmentDirections.actionNewPhrasesFragmentToUpdateFragment(themeList[position])
            holder.itemView.theme_background.findNavController().navigate(action)
        }
    }

    private fun playSound(soundName: String) {
        mp?.stop()
        mp?.release()
        mp = play(soundName)
    }

    private fun play(soundName: String): MediaPlayer {
        return MediaPlayer().apply {
            setDataSource(soundName)
            try {
                prepare()
            } catch (e: Exception) {
                Log.i("Ulchi", e.message.toString())
            }
            isLooping = false
            start()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.new_phrase, parent, false)
        return MyViewHolder(view)
    }

    fun setData(theme: List<NewPhrase>) {
        this.themeList = theme
        notifyDataSetChanged()
    }
}