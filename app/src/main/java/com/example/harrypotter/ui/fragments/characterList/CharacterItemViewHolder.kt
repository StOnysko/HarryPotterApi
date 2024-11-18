package com.example.harrypotter.ui.fragments.characterList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.harrypotter.databinding.CharacterItemBinding
import com.example.harrypotter.ui.models.Character
import com.example.harrypotter.util.UrlUtils.getImageByURL

class CharacterItemViewHolder(private val binding: CharacterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(character: Character) = with(binding) {
        characterHouse.text = character.house
        characterName.text = character.name
        loadingAnimation.visibility = View.VISIBLE

        characterImage.load(getImageByURL(character.image!!)) {
            listener(
                onStart = {
                    loadingAnimation.visibility = View.VISIBLE
                },
                onSuccess = { _, _ ->
                    loadingAnimation.visibility = View.GONE
                },
                onError = { _, _ ->
                    loadingAnimation.visibility = View.GONE
                }
            )
        }
    }

    companion object {
        fun from(parent: ViewGroup): CharacterItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = CharacterItemBinding.inflate(inflater, parent, false)
            return CharacterItemViewHolder(binding)
        }
    }
}