package com.example.studentregister

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentregister.databinding.ListItemBinding
import com.example.studentregister.db.Anime

class AnimeRecyclerViewAdapter(private val clickListener:(Anime) -> Unit): RecyclerView.Adapter<AnimeViewHolder>() {

    private val animeList = ArrayList<Anime>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        val listItem =  layoutInflater.inflate(binding.root,parent,false)
        return AnimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(animeList[position],clickListener)
    }

    override fun getItemCount(): Int {
        return animeList.size
    }

    fun setList(students:List<Anime>){
        animeList.clear()
        animeList.addAll(students)
    }

}


class AnimeViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(anime: Anime,clickListener:(Anime) -> Unit) {

//        val nameTextView = view.findViewById<TextView>(R.id.tvName)
//        val animeTextView = view.findViewById<TextView>(R.id.tvEmail)
        binding.apply {
            tvName.text = anime.name
            tvEmail.text = anime.dialogue

            root.setOnClickListener {
                clickListener(anime)
            }
        }
    }
}