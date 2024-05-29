package com.rahulyadav.imageloader.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahulyadav.imageloader.ImageLoaderInitializer
import com.rahulyadav.imageloader.databinding.ItemLayoutBinding
import com.rahulyadav.imageloader.model
import com.rahulyadav.imageloaderlibrary.ImageLoader

class Adapter(private val sourceList : ArrayList<model>) : RecyclerView.Adapter<Adapter.DataViewHolder>() {



    class DataViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(url : model) {
            ImageLoaderInitializer.get().apply {
                into(binding.image)
                load(url.coverageURL)
                show()
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder  = DataViewHolder(
        ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false ))

    override fun getItemCount(): Int = sourceList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(sourceList[position])
    }

    fun addData(list :List<model>) {
        sourceList.addAll(list)
    }

}