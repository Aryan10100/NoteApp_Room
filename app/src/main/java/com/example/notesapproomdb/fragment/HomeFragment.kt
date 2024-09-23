package com.example.notesapproomdb.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapproomdb.MainActivity
import com.example.notesapproomdb.R
import com.example.notesapproomdb.adapter.NoteAdapter
import com.example.notesapproomdb.databinding.FragmentHomeBinding
import com.example.notesapproomdb.model.NotesEntity
import com.example.notesapproomdb.viewmodel.NoteViewModel

class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener,
    MenuProvider {       //Extend the Fragment(R.layout.fragment_home) to get the home layout and we have a search view also extend it with .OnQueryTextListener and we also have a menu so extend it also with menu provider
    //Multiple functions to  display the note and search the note
    //Add data binding
    private var homeBinding: FragmentHomeBinding? = null
    private val binding get() = homeBinding!!

    //View model to connect with ui and recyclerView adapter
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //initializing the data binding
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        noteViewModel = (activity as MainActivity).noteViewModel
        setUpHomeRecyclerView()

        binding.addNoteFab.setOnClickListener {
            HomeFragmentDirections.actionHomeFragmentToAddNoteFragment().let { action ->
                findNavController().navigate(action)
            }
        }
    }

    private fun updateUI(note: List<NotesEntity>?) {
       if (note!= null){
           if (note.isNotEmpty()) {

               binding.emptyNotesImage.visibility = View.GONE
               binding.homeRecyclerView.visibility = View.VISIBLE

           } else {

               binding.emptyNotesImage.visibility = View.VISIBLE
               binding.homeRecyclerView.visibility = View.GONE
           }
       }
    }

    private fun setUpHomeRecyclerView() {
        noteAdapter =
            NoteAdapter()                                                                              //initialize the note adapter
        binding.homeRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )          //we want the layout to be in staggered layout so apply staggered layout
            setHasFixedSize(true)
            adapter = noteAdapter
        }
        activity?.let {                                                                     //we need to display the note in recycler View so use observe
            noteViewModel.getAllNote().observe(viewLifecycleOwner) { note ->
                noteAdapter.differ.submitList(note)
                updateUI(note)

            }
        }
    }

    private fun searchNote(query: String?) {
        val searchQuery = "%$query"

        noteViewModel.searchNote(searchQuery).observe(this) { list ->
            noteAdapter.differ.submitList(list)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false //when user try  to enter a query and submit it by clicking a button[we don't want it ]
    }

    override fun onQueryTextChange(newText: String?): Boolean {              //as user type the text the search continues
        if (newText !== null) {
            searchNote(newText)
        }
        return true
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.home_menu, menu)

        val menuSearch = menu.findItem(R.id.searchMenu).actionView as SearchView
        menuSearch.setOnQueryTextListener(this)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        homeBinding = null

    }

}