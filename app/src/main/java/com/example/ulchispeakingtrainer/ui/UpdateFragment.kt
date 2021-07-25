package com.example.ulchispeakingtrainer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ulchispeakingtrainer.R
import com.example.ulchispeakingtrainer.data.NewPhrase
import com.example.ulchispeakingtrainer.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val vm: SharedViewModel by viewModels()
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        view.russian_phrase.setText(args.phrase.russian.toString())
        view.ulchi_phrase.setText(args.phrase.ulchi.toString())

        view.save.setOnClickListener {
            vm.update(NewPhrase(args.phrase.id, view.russian_phrase.text.toString()
            , view.ulchi_phrase.text.toString(), args.phrase.voice))
            Toast.makeText(requireContext(), "Фраза обновлена!", Toast.LENGTH_SHORT).show()
            view.save.findNavController().navigateUp()
        }

        view.delete_btn.setOnClickListener {
          showAddDialog()
        }
        return view
    }

    private fun showAddDialog() {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.apply {
            setTitle("Хотите удалить фразу?")
            setCancelable(true)
            setPositiveButton("Да") {
                    _, _ ->    vm.deletePhrases(args.phrase.id)
                Toast.makeText(requireContext(), "Фраза удалена!", Toast.LENGTH_SHORT).show()
                view?.delete_btn?.findNavController()?.navigateUp()
            }
            setNegativeButton("Не") {
                    dialog, _ ->  dialog.dismiss()
            }
        }
        dialog.create()
        dialog.show()
    }

}