package com.example.harrypotter.ui.fragments.spellList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypotter.ui.viewmodel.SpellListFragmentViewModel
import com.example.harrypotter.databinding.FragmentSpellListBinding
import com.example.harrypotter.util.collectLifecycleAware
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpellListFragment : Fragment() {

    private val viewModel: SpellListFragmentViewModel by viewModels()
    private val spellListAdapter = SpellListAdapter()
    private var _binding: FragmentSpellListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpellListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUi() {
        with(binding) {
            spellsRcView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            spellsRcView.setHasFixedSize(false)
            spellsRcView.adapter = spellListAdapter
        }
    }

    private fun observeViewModel() {
       viewModel.spellListState.collectLifecycleAware(viewLifecycleOwner, spellListAdapter::submitList)
    }
}