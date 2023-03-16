package com.example.my_quotes.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.my_quotes.Model.Notes
import com.example.my_quotes.R
import com.example.my_quotes.Viewmodel.Notesviewmodel
import com.example.my_quotes.databinding.FragmentCreatefragmentBinding
import com.example.my_quotes.databinding.FragmentHomefragmentBinding
import java.util.*


class Createfragment : Fragment() {

    lateinit var binding: FragmentCreatefragmentBinding
    var priority: String = "1"

    val viewmodel: Notesviewmodel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreatefragmentBinding.inflate(layoutInflater, container, false)

        binding.floatDone.setOnClickListener {
            if(binding.title.text.isNotEmpty() && binding.subTitle.text.isNotEmpty()){
               // Toast.makeText(requireContext(), "Title or Sub-Title should be filled", Toast.LENGTH_SHORT).show()
                //view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_createfragment2_to_homefragment2) }
                //Log.e("oooo","hping  or not")
                createnotes(it)
                Navigation.findNavController(it!!).navigate(R.id.action_createfragment2_to_homefragment2)

            }
            else{
                Toast.makeText(requireContext(), "Title or Sub-Title should be filled", Toast.LENGTH_SHORT).show()
            }

        }
        binding.priGren.setImageResource(R.drawable.done_24)


        binding.priGren.setOnClickListener {
            priority = "1"
            binding.priGren.setImageResource(R.drawable.done_24)
            binding.priRed.setImageResource(0)
            binding.priYlo.setImageResource(0)
        }

        binding.priRed.setOnClickListener {
            priority = "2"
            binding.priGren.setImageResource(0)
            binding.priRed.setImageResource(R.drawable.done_24)
            binding.priYlo.setImageResource(0)
        }
        binding.priYlo.setOnClickListener {
            priority = "3"
            binding.priYlo.setImageResource(R.drawable.done_24)
            binding.priGren.setImageResource(0)
            binding.priRed.setImageResource(0)
        }


        return binding.root
    }

    private fun createnotes(it: View?) {
        val title = binding.title.text
        val sub_title = binding.subTitle.text
        val notes = binding.notes.text

        val d = Date()
        val s: CharSequence = DateFormat.format("MMMM d , yyyy", d.time)

        Log.e("@@@@@", "messege:$s")
        val data = Notes(
            null,
            title = title.toString(),
            sub_title = sub_title.toString(),
            notes = notes.toString(),
            date = s.toString(),
            priority = priority,
            selected = false
        )
        viewmodel.addnotes(data)




        // Toast.makeText(requireContext(), "make and submit a note", Toast.LENGTH_SHORT).show()

    }

}