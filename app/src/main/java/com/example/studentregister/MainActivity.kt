package com.example.studentregister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentregister.databinding.ActivityMainBinding
import com.example.studentregister.databinding.ListItemBinding
import com.example.studentregister.db.Anime
import com.example.studentregister.db.AnimeDatabase
import com.example.studentregister.db.AnimeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: AnimeViewModel
    private lateinit var adapter: AnimeRecyclerViewAdapter
    private  var isListItemClicked = false

    private lateinit var selectedAnime: Anime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = AnimeDatabase.getInstance(application).animeDao()
        val factory = AnimeViewModelFactory(dao)
        viewModel = ViewModelProvider(this,factory)[AnimeViewModel::class.java]

        binding.apply {
            btnSave.setOnClickListener {
                if(isListItemClicked){
                    updateStudentData()
                    clearInput()
                }
                else{
                    saveStudentData()
                    clearInput()
                }
            }
            btnClear.setOnClickListener {
                if(isListItemClicked){
                    deleteStudentData()
                    clearInput()
                }
                else{
                    clearInput()
                }
            }
        }
        initRecyclerView()
    }

    private fun saveStudentData(){
        binding.apply {
            viewModel.insertStudent(
                Anime(
                    0,
                    etName.text.toString(),
                    etDialogue.text.toString()

                )
            )
        }
    }

    private fun updateStudentData(){
        binding.apply {
            viewModel.updateStudent(
                Anime(
                    selectedAnime.id,
                    etName.text.toString(),
                    etDialogue.text.toString()
                )
            )
            btnSave.text = "Save"
            btnClear.text = "Clear"
            isListItemClicked = false
        }
    }

    private fun deleteStudentData(){
        binding.apply {
            viewModel.deleteStudent(
                Anime(
                    selectedAnime.id,
                    etName.text.toString(),
                    etDialogue.text.toString()
                )
            )
            btnSave.text = "Save"
            btnClear.text = "Clear"
            isListItemClicked = false
        }
    }

    private fun clearInput(){
        binding.apply {
            etName.setText("")
            etDialogue.setText("")
        }
    }

    private fun initRecyclerView(){
        binding.apply {
            rvStudent.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = AnimeRecyclerViewAdapter{
                    selectedItem:Anime -> listItemClicked(selectedItem)
            }
            rvStudent.adapter = adapter
        }
        displayStudentsList()
    }

    private fun displayStudentsList(){
        viewModel.animes.observe(this) {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun listItemClicked(anime: Anime){
        Toast.makeText(
            this,
            "${anime.name} is selected",
            Toast.LENGTH_SHORT
        ).show()

        binding.apply {
            selectedAnime = anime
            btnSave.text = "Update"
            btnClear.text = "Delete"
            isListItemClicked = true
            etName.setText(selectedAnime.name)
            etDialogue.setText(selectedAnime.dialogue)
        }
    }
}