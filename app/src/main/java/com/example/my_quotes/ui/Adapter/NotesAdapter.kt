package com.example.my_quotes.ui.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.my_quotes.Model.Notes
import com.example.my_quotes.Model.delete_item_list
import com.example.my_quotes.R
import com.example.my_quotes.databinding.ItemsLayoutBinding
import com.example.my_quotes.ui.Fragments.HomefragmentDirections


class NotesAdapter(
    val requireContext: Context,
    val noteslist: List<Notes>,
    private val deleteItemList: delete_item_list,
    private val showdeletemenu: (Boolean) -> Unit
) : RecyclerView.Adapter<NotesAdapter.notesviewholder>() {
    class notesviewholder(val binding: ItemsLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    private var isenable  = false
    private var itemselectedlist = ArrayList<Int>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesviewholder {
        return notesviewholder(
            ItemsLayoutBinding.inflate(LayoutInflater.from(parent.context)
                , parent
                , false)
        )
    }

    override fun onBindViewHolder(holder: notesviewholder, position: Int) {
        val data = noteslist[position]
        holder.binding.itemTitle.text = data.title
        holder.binding.itemData.text = data.notes
        holder.binding.itemDate.text = data.date
       // holder.binding.itemView.setImageResource(data.priority.toInt())
       // holder.binding.selectView.isVisible =  false
       // showdeletemenu(false)


        when(data.priority){
            "1" -> {holder.binding.itemView.setBackgroundResource(R.drawable.circular_img1)}
            "2" -> {holder.binding.itemView.setBackgroundResource(R.drawable.circular_img2)}
            "3" -> {holder.binding.itemView.setBackgroundResource(R.drawable.circular_img3)}
        }

        holder.binding.root.setOnClickListener{
            if(itemselectedlist.contains(data.id)){
               // Log.e("@@@@@", "contains the position but now deleted")
                    itemselectedlist.remove(data.id)
               // Log.e("@@@@@", "messege : $itemselectedlist")
                    data.selected = false
                    holder.binding.selectView.isVisible =  false

                 if(itemselectedlist.isEmpty()){
                   //  Log.e("@@@@@", "messege:qwqwqwqwqwqw")
                    showdeletemenu(false)
                    isenable = false
                }
            }
            else if (isenable){
                holding(holder , data , position)
            }
            else{
                if(itemselectedlist.isEmpty()){
                    val action = HomefragmentDirections.actionHomefragment2ToEditfragment2(data)
                    Navigation.findNavController(it).navigate(action)
                }
            }

        }

        holder.binding.root.setOnLongClickListener{
          //  Toast.makeText(requireContext , "it is working",Toast.LENGTH_SHORT).show()
            holding(holder , data , position)
            true
        }

    }

    private fun holding(holder: notesviewholder, data: Notes, position: Int) {
        holder.binding.selectView.isVisible =  true
        isenable = true
        data.id?.let { itemselectedlist.add(it) }
        data.selected = true
        Log.e("@@@@@", "messege:bhnbcgggytdtrfuh")
        showdeletemenu(false)
        deleteItemList.getlist(itemselectedlist)
        Toast.makeText(requireContext , "it is working" + itemselectedlist,Toast.LENGTH_SHORT).show()
    }

    override fun getItemCount() = noteslist.size

}

