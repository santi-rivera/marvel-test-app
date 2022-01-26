package es.santirivera.pruebamarvel.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.pruebamarvel.MarvelFragment
import es.santirivera.pruebamarvel.databinding.FragmentItemListBinding
import es.santirivera.pruebamarvel.util.EndlessRecyclerViewScrollListener

@AndroidEntryPoint
class CharacterListFragment : MarvelFragment(),
    MarvelCharacterViewHolder.OnCharacterClickedCallback {

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    private val characterListViewModel: CharacterListViewModel by viewModels()

    private val adapter = MarvelCharacterAdapter(ArrayList<MarvelCharacter>(), this)

    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

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
            endLoad()
            adapter.setNewValues(it)
        }
        characterListViewModel.exception.observe(viewLifecycleOwner) {
            val retry = handleErrors(it)
            scrollListener.loadFailed()
            if (retry) {
                characterListViewModel.requestCharacters(adapter.itemCount + 1)
            }
        }
        startLoad()
    }


    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = adapter
        binding.itemList.apply {
            scrollListener = object :
                EndlessRecyclerViewScrollListener(
                    binding.itemList.layoutManager as LinearLayoutManager,
                    10
                ) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    characterListViewModel.requestCharacters(totalItemsCount)
                    startLoad()
                }
            }
            addOnScrollListener(scrollListener)
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