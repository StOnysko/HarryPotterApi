package com.example.harrypotter.ui.fragments.characterList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypotter.Houses
import com.example.harrypotter.databinding.FragmentCharacterListBinding
import com.example.harrypotter.ui.models.Character
import com.example.harrypotter.ui.viewmodel.CharacterListFragmentViewModel
import com.example.harrypotter.util.collectLifecycleAware
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment(), OnItemClickListener {

    private val characterListAdapter = CharacterListAdapter(this)
    private val viewModel: CharacterListFragmentViewModel by viewModels()
    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        observeViewModel()
        filterList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUi() {
        with(binding) {
            charactersRcView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            charactersRcView.setHasFixedSize(false)
            charactersRcView.adapter = characterListAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.allCharacter.collectLifecycleAware(
            viewLifecycleOwner,
            characterListAdapter::submitList
        )
    }

    private fun filterList() {
        val houses = mapOf(
            binding.allHouses to Houses.ALL_HOUSES,
            binding.gryffindorFilter to Houses.GRYFFINDOR,
            binding.slytherinFilter to Houses.SLYTHERIN,
            binding.hufflepuffFilter to Houses.HUFFLEPUFF,
            binding.ravenclawFilter to Houses.RAVENCLAW
        )

        houses.forEach { (button, house) ->
            button.setOnClickListener {
                viewModel.loadCharactersByHouse(house.hName)
            }
        }
    }

    override fun onCharacterClickListener(character: Character) {
        val action = CharacterListFragmentDirections
            .actionCharacterListFragmentToCharacterFragment(character)
        findNavController().navigate(action)
    }
}