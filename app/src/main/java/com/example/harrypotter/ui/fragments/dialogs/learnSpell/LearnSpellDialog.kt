package com.example.harrypotter.ui.fragments.dialogs.learnSpell

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypotter.Houses
import com.example.harrypotter.R
import com.example.harrypotter.databinding.LearnSpellDialogBinding
import com.example.harrypotter.ui.fragments.character.OnHouseClick
import com.example.harrypotter.ui.fragments.dialogs.changeHouse.ChangeHouseListAdapter
import com.example.harrypotter.ui.models.Spell
import com.example.harrypotter.ui.viewmodel.CharacterScreenViewModel
import com.example.harrypotter.util.collectLifecycleAware
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LearnSpellDialog : DialogFragment() {

    private var isSpellDialog: Boolean = true
    private lateinit var houseListAdapter: ChangeHouseListAdapter
    private lateinit var spellListAdapter: LearnSpellListAdapter
    private val viewModel: CharacterScreenViewModel by viewModels()
    private var _binding: LearnSpellDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LearnSpellDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val argument = arguments?.getBoolean(IS_SPELL_DIALOG)
        if (argument != null) {
            isSpellDialog = argument
        }
        initListAdapter()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.let { window ->
            val params = window.attributes
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            window.attributes = params
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initListAdapter() {
        if (isSpellDialog) {
            initSpellListUi()
        } else {
            initHouseListUi()
        }
    }

    private fun initHouseListUi() {
        houseListAdapter = ChangeHouseListAdapter(object : OnHouseClick {
            override fun onHouseClick(houses: Houses) {
                parentFragmentManager.setFragmentResult(RESULT_KEY, bundleOf(HOUSE_RESULT to houses))
                dismiss()
            }
        })
        houseListAdapter.submitList(
            listOf(
                Houses.RAVENCLAW,
                Houses.SLYTHERIN,
                Houses.GRYFFINDOR,
                Houses.HUFFLEPUFF
            )
        )
        with(binding) {
            dialogHeader.text = getString(R.string.change_house)
            dialogSpellsRcView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            dialogSpellsRcView.setHasFixedSize(false)
            dialogSpellsRcView.adapter = houseListAdapter
        }
    }

    private fun initSpellListUi() {
        spellListAdapter = LearnSpellListAdapter(object : OnSpellClickListener {
            override fun onSpellClick(spell: Spell) {
                parentFragmentManager.setFragmentResult(RESULT_KEY, bundleOf(SPELL_RESULT to spell))
                dismiss()
            }
        })
        viewModel.spellListState.collectLifecycleAware(
            viewLifecycleOwner,
            spellListAdapter::submitList
        )
        with(binding) {
            dialogHeader.text = getString(R.string.spells_to_learn)
            dialogSpellsRcView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            dialogSpellsRcView.setHasFixedSize(false)
            dialogSpellsRcView.adapter = spellListAdapter
        }
    }

    companion object {
        const val SPELL_RESULT = "LearnedSpellDialog"
        const val HOUSE_RESULT = "ChangeHouseDialog"
        const val RESULT_KEY = "LearnedSpellDialogKey"
        private const val IS_SPELL_DIALOG = "IsSpellDialog"

        fun newInstance(isSpellDialog: Boolean): LearnSpellDialog = LearnSpellDialog().apply {
            this.arguments = bundleOf(IS_SPELL_DIALOG to isSpellDialog)
        }
    }
}

