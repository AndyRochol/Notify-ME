package com.example.my_quotes.ui.Fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.my_quotes.Model.delete_item_list
import com.example.my_quotes.R
import com.example.my_quotes.Viewmodel.Notesviewmodel
import com.example.my_quotes.databinding.FragmentHomefragmentBinding
import com.example.my_quotes.ui.Adapter.NotesAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog


class Homefragment : Fragment(), delete_item_list {

    lateinit var binding: FragmentHomefragmentBinding
    val viewmodel : Notesviewmodel by viewModels()
   // val menuHost: MenuHost = requireActivity()
    private var menumain : Menu? = null
    private var list = ArrayList<Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomefragmentBinding.inflate(layoutInflater, container, false)

        binding.floatBtn.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homefragment2_to_createfragment2)
        }

        viewmodel()

        binding.imgbtn.setOnClickListener {
            viewmodel()
        }

        binding.highview.setOnClickListener{
            viewmodel.highnotes().observe(viewLifecycleOwner) { noteslist ->
                binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recyclerView.adapter = NotesAdapter(requireContext(), noteslist,this){ showmenuoption(show = true)}

            }
        }

        binding.mediumview.setOnClickListener{
            viewmodel.mediumnotes().observe(viewLifecycleOwner) { noteslist ->
                binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recyclerView.adapter = NotesAdapter(
                    requireContext(),
                    noteslist,
                    this,

                ){ showmenuoption(false)}

            }


        }

        binding.lowview.setOnClickListener{
            viewmodel.lownotes().observe(viewLifecycleOwner) { noteslist ->
                binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recyclerView.adapter = NotesAdapter(
                    requireContext(),
                    noteslist,
                    this,

                ){ showmenuoption(show = false)}

            }
        }


        val menuHost: MenuHost = requireHost() as MenuHost
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menumain = menu
                showmenuoption(true)
                menuInflater.inflate(R.menu.menu_list, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.menu_item -> {
                        // todo menu1
                        val bottomSheetDialog = BottomSheetDialog(requireContext())
                        bottomSheetDialog.setContentView(R.layout.dialog_delete)
                        bottomSheetDialog.show()

                        val textyes = bottomSheetDialog.findViewById<TextView>(R.id.dialog_yes)
                        val textno = bottomSheetDialog.findViewById<TextView>(R.id.dialog_no)

                        textno?.setOnClickListener{
                            bottomSheetDialog.dismiss()
                        }

                        textyes?.setOnClickListener {
                            Toast.makeText(requireContext(),"the ids is"+ list , Toast.LENGTH_SHORT).show()
                           /* val number = list.iterator()
                            Log.e("@@@@@", "messege:kjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj
                            ")
                            while (number.hasNext()){
                                Log.e("@@@@@", "messege:${number.next()}")
                                viewmodel.deletenotes(number.next())
                            }*/
                            viewmodel.deletenotes(list)
                            Log.e("@@@@@", "messege:$list")
                            list.clear()
                            bottomSheetDialog.dismiss()
                        }

                        showmenuoption(false)
                        return true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    private fun showmenuoption(show: Boolean) {
        menumain?.findItem(R.id.menu_item)?.isVisible = show
    }

   // private fun Deleted(): Boolean {

       // val alertDialog = AlertDialog.Builder(requireContext())
       // alertDialog.setTitle("Delete")
      //  alertDialog.setMessage("Do you want to delete this note?")
      //  alertDialog.setPositiveButton("Yes"){_,_ ->

       //     showmenuoption(false)
      //  }
      //  alertDialog.setNegativeButton("NO"){_,_ ->
      //      alertDialog.show()
     //   }
   // return false

    //}


    private fun viewmodel(){
        Log.e("@@@@@", "messege:kjkjkjkjkjkyyyyyyyyyyys")
        viewmodel.getnotes().observe(viewLifecycleOwner) { noteslist  ->
            binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.recyclerView.adapter = NotesAdapter(
                requireContext(),
                noteslist,
                this,

            ){ showmenuoption( true)}

        }
    }

    override fun getlist(itemlist: ArrayList<Int>) {
        list.addAll(itemlist)
    }


}