package com.example.my_quotes.ui.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.my_quotes.Model.Notes
import com.example.my_quotes.R
import com.example.my_quotes.Viewmodel.Notesviewmodel
import com.example.my_quotes.databinding.FragmentEditfragmentBinding
import java.text.DateFormat
import java.util.*


class Editfragment : Fragment() {

    val Notes by navArgs<EditfragmentArgs>()
    lateinit var binding: FragmentEditfragmentBinding
    var priority: String = "1"
    val viewmodel: Notesviewmodel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditfragmentBinding.inflate(LayoutInflater.from(requireContext()), container, false)

        binding.teTitle.setText(Notes.sharingNotes.title)
        binding.subTitleEdit.setText(Notes.sharingNotes.sub_title)
        binding.notesEdit.setText(Notes.sharingNotes.notes)
        when (Notes.sharingNotes.priority) {
            "3" -> {
                priority = "3"
                binding.priGrenEdot.setImageResource(R.drawable.done_24)
                binding.priRedEdit.setImageResource(0)
                binding.priYloEdit.setImageResource(0)
            }

            "1" -> {
                binding.priGrenEdot.setImageResource(0)
                binding.priRedEdit.setImageResource(R.drawable.done_24)
                binding.priYloEdit.setImageResource(0)
            }

            "2" -> {
                binding.priGrenEdot.setImageResource(0)
                binding.priRedEdit.setImageResource(0)
                binding.priYloEdit.setImageResource(R.drawable.done_24)
            }
        }

        binding.priGrenEdot.setOnClickListener {
            priority = "1"
            binding.priGrenEdot.setImageResource(0)
            binding.priRedEdit.setImageResource(R.drawable.done_24)
            binding.priYloEdit.setImageResource(0)
        }

        binding.priRedEdit.setOnClickListener {
            priority = "2"
            binding.priGrenEdot.setImageResource(0)
            binding.priRedEdit.setImageResource(0)
            binding.priYloEdit.setImageResource(R.drawable.done_24)
        }
        binding.priYloEdit.setOnClickListener {
            priority = "3"
            binding.priYloEdit.setImageResource(0)
            binding.priGrenEdot.setImageResource(R.drawable.done_24)
            binding.priRedEdit.setImageResource(0)
        }

        binding.editFloatDone.setOnClickListener {
            updatenotes(it)
        }

        return binding.root
    }

    private fun updatenotes(it: View?) {

        val title = binding.teTitle.text.toString()
        val subtitle = binding.subTitleEdit.text.toString()
        val notes = binding.notesEdit.text.toString()
        val d = Date()
        val s: CharSequence = android.text.format.DateFormat.format("MMMM d , yyyy", d.time)

        val data = Notes(
            Notes.sharingNotes.id,
            title = title,
            sub_title = subtitle,
            notes = notes,
            date = s.toString(),
            priority = priority,
            selected = false
        )
        Log.e("#####","messege :${Notes.sharingNotes.id}")
        viewmodel.updatenotes(data)
        Navigation.findNavController(it!!).navigate(R.id.action_editfragment2_to_homefragment2)

    }


}