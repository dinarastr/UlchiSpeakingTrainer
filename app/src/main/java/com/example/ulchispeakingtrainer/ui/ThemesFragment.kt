package com.example.ulchispeakingtrainer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ulchispeakingtrainer.R
import com.example.ulchispeakingtrainer.adapters.ThemeAdapter
import com.example.ulchispeakingtrainer.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_themes.view.*

class ThemesFragment : Fragment() {

    private val vm: SharedViewModel by viewModels()
    private val adapter: ThemeAdapter by lazy { ThemeAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_themes, container, false)

        val recycler = view.themes_rv
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        vm.allThemes.observe(viewLifecycleOwner, Observer { data ->
            adapter.setData(data)
        })
        return view
    }
}