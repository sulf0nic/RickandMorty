package com.ebc.rickmortyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ebc.rickmortyapp.models.RickMorty
import com.ebc.rickmortyapp.adapter.RickMortyPagedAdapter.*
import com.ebc.rickmortyapp.databinding.RickMortyLayoutBinding

class RickMortyPagedAdapter:PagingDataAdapter<RickMorty, MyViewHolder>(diffCallback ) {

    inner class MyViewHolder(val binding: RickMortyLayoutBinding):
    RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<RickMorty>(){
            override fun areItemsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RickMorty, newItem: RickMorty): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)

        //este metodo getItem() es para PagingDataAdapter...
        holder.binding.apply {
            textView.text= "${currentItem?.name}"
            val imageLink = currentItem?.image


            //Aqui se usara corotine image loader para mostrar en las imagenes
            imageView.load(imageLink){
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RickMortyLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }
}