package com.example.harrypotter.ui.fragments.dialogs.changeHouse

import androidx.recyclerview.widget.DiffUtil
import com.example.harrypotter.Houses

class HouseItemDiffUtil : DiffUtil.ItemCallback<Houses>() {
    override fun areItemsTheSame(oldItem: Houses, newItem: Houses): Boolean {
        return oldItem.hName == newItem.hName
    }

    override fun areContentsTheSame(oldItem: Houses, newItem: Houses): Boolean {
        return oldItem == newItem
    }
}