package com.example.ulchispeakingtrainer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ulchispeakingtrainer.R
import com.example.ulchispeakingtrainer.adapters.PhraseAdapter
import com.example.ulchispeakingtrainer.adapters.ThemeAdapter
import com.example.ulchispeakingtrainer.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_themes.view.*

class PhrasesFragment : Fragment() {


    private val vm: SharedViewModel by viewModels()
    private val args by navArgs<PhrasesFragmentArgs>()
    private val adapter: PhraseAdapter by lazy { PhraseAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_phrases, container, false)
        val recycler = view.themes_rv
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        vm.getAllPhrasesByTheme(args.currentTheme.ulchi.toString()).observe(viewLifecycleOwner, Observer { data ->
            adapter.setData(data)
        })
        return view
    }
}