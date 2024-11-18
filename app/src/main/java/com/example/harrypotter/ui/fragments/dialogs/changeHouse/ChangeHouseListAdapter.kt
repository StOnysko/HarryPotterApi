package com.example.harrypotter.ui.fragments.dialogs.changeHouse

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.harrypotter.Houses
import com.example.harrypotter.ui.fragments.character.OnHouseClick

class ChangeHouseListAdapter(private val houseItemClickListener: OnHouseClick) :
    ListAdapter<Houses, ChangeHouseItemViewHolder>(HouseItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChangeHouseItemViewHolder {
        return ChangeHouseItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ChangeHouseItemViewHolder, position: Int) {
        val house = getItem(position)
        holder.bind(house)
        holder.itemView.setOnClickListener {
            houseItemClickListener.onHouseClick(house)
        }
    }
}