package com.example.notesapproomdb.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapproomdb.MainActivity
import com.example.notesapproomdb.R
import com.example.notesapproomdb.databinding.FragmentEditNoteBinding
import com.example.notesapproomdb.model.NotesEntity
import com.example.notesapproomdb.viewmodel.NoteViewModel

class EditNoteFragment : Fragment(R.layout.fragment_edit_note),MenuProvider {

    private var editNoteBinding :FragmentEditNoteBinding ? =null
    private val binding get() = editNoteBinding!!

    private  lateinit var notesViewModel: NoteViewModel
    private lateinit var  currentNote : NotesEntity

    private  val args :EditNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        editNoteBinding = FragmentEditNoteBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost : MenuHost = requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)

        notesViewModel =(activity as MainActivity).noteViewModel
        currentNote =args.note!!

        binding.editNoteTitle.setText(currentNote.notesTitle)                                  //respective note and title will be displayed on  their fields
        binding.editNoteDesc.setText(currentNote.notesDesc)

        binding.editNoteFab.setOnClickListener {
            val notesTitle = binding.editNoteTitle.text.toString().trim()
            val notesDesc = binding.editNoteDesc.text.toString().trim()

            if(notesTitle.isNotEmpty()){
                val note = NotesEntity(currentNote.id,notesTitle,notesDesc)
                notesViewModel.updateNote(note)

                view.findNavController().popBackStack(R.id.homeFragment,false)
                Toast.makeText(context,"Note Saved",Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(context,"Please enter the Title",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun delete(){
        AlertDialog.Builder(requireActivity()).apply {
               setTitle("Delete Note")
               setMessage("Do you want to delete the note ?")
               setPositiveButton("Delete"){_,_ ->
                   notesViewModel.deleteNote(currentNote)
                   Toast.makeText(context,"Note Deleted",Toast.LENGTH_SHORT).show()
                   view?.findNavController()?.popBackStack(R.id.homeFragment,false)
               }
            setNegativeButton("Cancel",null)
        }.create().show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_edit_note,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
      return when(menuItem.itemId){
          R.id.deleteMenu ->{
              delete()
              true
          }else -> false
      }
    }
    override fun onDestroy() {
        super.onDestroy()
        editNoteBinding = null
    }

}