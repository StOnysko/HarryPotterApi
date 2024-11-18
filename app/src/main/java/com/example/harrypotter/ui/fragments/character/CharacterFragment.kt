package com.example.harrypotter.ui.fragments.character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.harrypotter.Houses
import com.example.harrypotter.databinding.FragmentCharacterBinding
import com.example.harrypotter.ui.fragments.dialogs.learnSpell.LearnSpellDialog
import com.example.harrypotter.ui.fragments.dialogs.learnSpell.OnSpellClickListener
import com.example.harrypotter.ui.models.Spell
import com.example.harrypotter.ui.viewmodel.CharacterScreenViewModel
import com.example.harrypotter.util.UrlUtils
import com.example.harrypotter.util.UrlUtils.getHouseByUrl
import com.example.harrypotter.util.UrlUtils.getImageByURL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment : Fragment() {
    private val characterArgs by navArgs<CharacterFragmentArgs>()
    private lateinit var learnedSpellsAdapter: CharacterSpellListAdapter
    private val viewmodel: CharacterScreenViewModel by viewModels()
    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager.setFragmentResultListener(
            LearnSpellDialog.RESULT_KEY,
            viewLifecycleOwner
        ) { _, bundle ->
            val spellResult = bundle.getParcelable<Spell>(LearnSpellDialog.SPELL_RESULT)
            if (spellResult != null) {
                viewmodel.saveLearnedSpell(spellResult)
            }
            val houseResult = bundle.getSerializable(LearnSpellDialog.HOUSE_RESULT) as? Houses
            if (houseResult != null) {
                viewmodel.changeHouse(houseResult.hName)
            }
        }
        initUi()
        openDialog()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUi() {
        viewmodel.loadCharacterByID(characterArgs.character.characterId)
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewmodel.character.collect { character ->
                with(binding) {
                    characterAncestry.text = UrlUtils.getStringOrUnknown(character.ancestry)
                    characterSpecies.text = UrlUtils.getStringOrUnknown(character.species)
                    characterBirth.text = UrlUtils.getStringOrUnknown(character.dateOfBirth)
                    characterActor.text = UrlUtils.getStringOrUnknown(character.actor)
                    characterGenre.text = UrlUtils.getStringOrUnknown(character.gender)
                    characterHouse.text = getHouseByUrl(character.house)
                    characterImage.load(getImageByURL(character.image))
                    characterName.text = character.name

                    learnedSpellsAdapter = CharacterSpellListAdapter(object : OnSpellClickListener {
                        override fun onSpellClick(spell: Spell) {
                            Toast.makeText(requireContext(), spell.name, Toast.LENGTH_SHORT).show()
                        }
                    })

                    characterSpellsRcView.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    characterSpellsRcView.setHasFixedSize(false)
                    characterSpellsRcView.adapter = learnedSpellsAdapter
                }
                learnedSpellsAdapter.submitList(character.spells)

            }
        }
    }

    private fun openDialog() {
        binding.openSpellsBtn.setOnClickListener {
            LearnSpellDialog.newInstance(true).show(parentFragmentManager, "FragmentDialog")
        }
        binding.changeHouseBtn.setOnClickListener {
            LearnSpellDialog.newInstance(false).show(parentFragmentManager, "FragmentDialog")
        }
    }
}