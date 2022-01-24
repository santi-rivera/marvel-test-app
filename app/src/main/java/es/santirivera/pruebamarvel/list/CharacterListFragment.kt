package es.santirivera.pruebamarvel.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.pruebamarvel.R
import es.santirivera.pruebamarvel.databinding.FragmentItemListBinding
import es.santirivera.pruebamarvel.util.EndlessRecyclerViewScrollListener
import java.net.UnknownHostException

@AndroidEntryPoint
class CharacterListFragment : Fragment(), MarvelCharacterViewHolder.OnCharacterClickedCallback {

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    private val characterListViewModel: CharacterListViewModel by viewModels()

    private val adapter = MarvelCharacterAdapter(ArrayList<MarvelCharacter>(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = binding.itemList
        setupRecyclerView(recyclerView)
        characterListViewModel.characterList.observe(viewLifecycleOwner) {
            adapter.addAll(it)
        }
        characterListViewModel.error.observe(viewLifecycleOwner) {
            when (it) {
                is UnknownHostException -> Toast.makeText(
                    requireContext(),
                    R.string.unknown_host,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter
        binding.itemList.apply {
            val endlessScrollListener = object :
                EndlessRecyclerViewScrollListener(binding.itemList.layoutManager as LinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    characterListViewModel.requestCharacters(totalItemsCount)
                }
            }
            addOnScrollListener(endlessScrollListener)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCharacterClicked(item: MarvelCharacter) {
        findNavController().navigate(
            CharacterListFragmentDirections.showItemDetail(item, item.name)
        )
    }
}