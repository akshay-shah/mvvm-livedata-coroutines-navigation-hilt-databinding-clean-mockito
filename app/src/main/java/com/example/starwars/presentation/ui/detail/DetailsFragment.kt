package com.example.starwars.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.starwars.R
import com.example.starwars.databinding.FragmentDetailsBinding
import com.example.starwars.presentation.model.CharacterInfoModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var infoModel: CharacterInfoModel? = null
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false
        )
        with(binding) {
            lifecycleOwner = this@DetailsFragment
            viewModel = this@DetailsFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            infoModel = it.getParcelable(CHARACTER_DETAIL)
        }
        viewModel.init(infoModel)
    }

    companion object {
        private const val CHARACTER_DETAIL = "CHARACTER_DETAIL"
    }

}