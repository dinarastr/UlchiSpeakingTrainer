package ru.dinarastepina.ulchispeakingtrainer.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.theme_row.view.*
import ru.dinarastepina.ulchispeakingtrainer.R
import ru.dinarastepina.ulchispeakingtrainer.data.Theme
import ru.dinarastepina.ulchispeakingtrainer.ui.ThemesFragmentDirections

class ThemeAdapter: RecyclerView.Adapter<ThemeAdapter.MyViewHolder>() {
    var themeList = emptyList<Theme>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.theme_row, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return themeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.ulchi_theme.text = themeList[position].ulchi
        holder.itemView.russian_theme.text = themeList[position].russian
        Glide.with(holder.itemView.context)
            .load(Uri.parse("file:///android_asset/" + themeList[position].picture))
            .into(holder.itemView.theme_image)
        holder.itemView.theme_background.setOnClickListener {
            val action = ThemesFragmentDirections.actionThemesFragmentToPhrasesFragment(themeList[position])
            holder.itemView.theme_background.findNavController().navigate(action)
        }
    }

    fun setData(theme: List<Theme>) {
        this.themeList = theme
        notifyDataSetChanged()
    }
}