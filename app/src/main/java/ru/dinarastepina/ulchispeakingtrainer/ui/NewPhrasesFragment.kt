package ru.dinarastepina.ulchispeakingtrainer.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_new_phrases.*
import kotlinx.android.synthetic.main.fragment_new_phrases.view.*
import ru.dinarastepina.ulchispeakingtrainer.R
import ru.dinarastepina.ulchispeakingtrainer.adapters.NewPhraseAdapter
import ru.dinarastepina.ulchispeakingtrainer.viewmodel.SharedViewModel
import java.lang.Exception


class NewPhrasesFragment : Fragment() {
    private val vm: SharedViewModel by viewModels()
    private val adapter: NewPhraseAdapter by lazy { NewPhraseAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_phrases, container, false)
        val recycler = view.new_phrases_rv
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        vm.allNewPhrases.observe(viewLifecycleOwner, Observer { data ->
            vm.checkIfEmpty(data)
            adapter.setData(data)
        })
        vm.emptyData.observe(viewLifecycleOwner, Observer {
            showEmptyDb(it)
        })
        val addNewPhrase = NewPhrasesFragmentDirections.actionNewPhrasesFragmentToAddNewPhraseFragment()
        view.fab_add_audio.setOnClickListener {
            try {
                fab_add_audio.findNavController().navigate(addNewPhrase)
            } catch (e: Exception) {
                Log.i("Dinara   ", e.message.toString())
            }
        }
        return view
    }

    private fun showEmptyDb(isDBEmpty: Boolean) {
        if (isDBEmpty) {
            view?.empty_error?.visibility = View.VISIBLE
        } else {
            view?.empty_error?.visibility = View.INVISIBLE
        }
    }
}