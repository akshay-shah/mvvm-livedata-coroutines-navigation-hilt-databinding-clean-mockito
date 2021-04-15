package com.example.starwars.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwars.R
import com.example.starwars.databinding.FragmentHomeBinding
import com.example.starwars.presentation.ui.home.adapter.SearchResultAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: SearchResultAdapter
    var bundle: Bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val onItemClickListener: (position: Int) -> Unit = {
            viewModel.onListItemClickListener(it)
        }
        adapter = SearchResultAdapter(onItemClickListener)
        with(viewModel) {
            characterNameList.observe(this@HomeFragment, {
                adapter.setList(it.characterNamesList)
            })
            characterDetail.observe(this@HomeFragment, {
                bundle.putParcelable(CHARACTER_DETAIL, it)
                navigateToDetails(bundle)
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        with(binding) {
            lifecycleOwner = this@HomeFragment
            viewModel = this@HomeFragment.viewModel
            searchViewCharacterSearch.setOnQueryTextListener(viewModel?.queryTextListener)
            recyclerViewCharacterList.adapter = adapter
            recyclerViewCharacterList.layoutManager =
                LinearLayoutManager(recyclerViewCharacterList.context)
        }
        return binding.root
    }

    private fun navigateToDetails(bundle: Bundle) {
        findNavController().navigate(
            R.id.navigateToDetailsFragment,
            bundle
        )
    }

    companion object {
        private const val CHARACTER_DETAIL = "CHARACTER_DETAIL"
    }
}