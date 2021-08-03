package ru.dinarastepina.ulchispeakingtrainer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.text_row.view.*
import kotlinx.android.synthetic.main.theme_row.view.*
import kotlinx.android.synthetic.main.theme_row.view.russian_theme
import kotlinx.android.synthetic.main.theme_row.view.theme_background
import kotlinx.android.synthetic.main.theme_row.view.theme_image
import kotlinx.android.synthetic.main.theme_row.view.ulchi_theme
import ru.dinarastepina.ulchispeakingtrainer.R
import ru.dinarastepina.ulchispeakingtrainer.data.UlchiText
import ru.dinarastepina.ulchispeakingtrainer.ui.TextsFragmentDirections

class TextAdapter: RecyclerView.Adapter<TextAdapter.MyViewHolder>() {
    var themeList = emptyList<UlchiText>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.text_row, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return themeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.ulchi_theme.text = themeList[position].ulchitext
        holder.itemView.ulchi_theme.maxLines = 1
        holder.itemView.russian_theme.text = themeList[position].russiantext
        holder.itemView.russian_theme.maxLines = 1
        holder.itemView.theme_image.setImageResource(R.drawable.ic_baseline_menu_book_24)


        holder.itemView.theme_background.setOnClickListener {
            val action =
                TextsFragmentDirections.actionTextsFragmentToAudioFragment(themeList[position])
            holder.itemView.theme_background.findNavController().navigate(action)
        }
    }

    fun setData(theme: List<UlchiText>) {
        this.themeList = theme
        notifyDataSetChanged()
    }
}