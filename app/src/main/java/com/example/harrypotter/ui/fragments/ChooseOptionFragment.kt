package com.example.harrypotter.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.harrypotter.R
import com.example.harrypotter.databinding.FragmentChooseOptionBinding

class ChooseOptionFragment : Fragment() {

    private var _binding: FragmentChooseOptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseOptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToFragments()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToFragments() = binding.apply {
        showSpellGuideBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_chooseOptionFragment2_to_spellListFragment
            )
        }

        showCharactersBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_chooseOptionFragment2_to_characterListFragment
            )
        }
    }
}