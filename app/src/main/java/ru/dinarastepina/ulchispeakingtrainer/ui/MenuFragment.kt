package ru.dinarastepina.ulchispeakingtrainer.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_menu.view.*
import ru.dinarastepina.ulchispeakingtrainer.R

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        val openThemes = MenuFragmentDirections.actionMenuFragmentToThemesFragment()
        val openTexts = MenuFragmentDirections.actionMenuFragmentToTextsFragment()
        val openMyAudios = MenuFragmentDirections.actionMenuFragmentToNewPhrasesFragment()
        view.speaker.setOnClickListener {
            speaker.findNavController().navigate(openThemes)
        }
        view.texts.setOnClickListener {
            texts.findNavController().navigate(openTexts)
        }
        view.myaudios.setOnClickListener {
            myaudios.findNavController().navigate(openMyAudios)
        }
        setHasOptionsMenu(true)
        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_click ->
                openInfo()
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    private fun openInfo(): Boolean {
        myaudios.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToAboutFragment())
        return true
    }
}