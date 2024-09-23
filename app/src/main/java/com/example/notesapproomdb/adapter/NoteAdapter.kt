package com.example.notesapproomdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapproomdb.databinding.NoteLayoutBinding
import com.example.notesapproomdb.fragment.HomeFragmentDirections
import com.example.notesapproomdb.model.NotesEntity

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    class NoteViewHolder(val itemBinding: NoteLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<NotesEntity>() {                //ItemCallBack is the interface used by DiffUtil to determine the difference between two list
        override fun areItemsTheSame(oldItem: NotesEntity, newItem: NotesEntity): Boolean {       //implementing the object we get the 2 member functions i.e. areItemsTheSame and areContentsTheSame
            return oldItem.id == newItem.id &&
                    oldItem.notesTitle == newItem.notesTitle &&
                    oldItem.notesDesc == newItem.notesDesc
        }

        override fun areContentsTheSame(oldItem: NotesEntity, newItem: NotesEntity): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)                                     //AsyncListDiffer is used to determine the difference between two list in the background Thread
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        )
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote =differ.currentList[position]              //defining the position of

        holder.itemBinding.noteDesc.text= currentNote.notesDesc
        holder.itemBinding.noteTitle.text=currentNote.notesTitle

        holder.itemView.setOnClickListener{

            val directions = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
            it.findNavController().navigate(directions)
        }
    }

}
