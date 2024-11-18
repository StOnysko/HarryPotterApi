package com.example.harrypotter.ui.fragments.dialogs.changeHouse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypotter.Houses
import com.example.harrypotter.databinding.HouseItemBinding

class ChangeHouseItemViewHolder(private val binding: HouseItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(houses: Houses) {
        binding.houseName.text = houses.hName
    }

    companion object {

        fun from(parent: ViewGroup): ChangeHouseItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = HouseItemBinding.inflate(inflater, parent, false)
            return ChangeHouseItemViewHolder(binding)
        }
    }
}