package es.santirivera.pruebamarvel.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.pruebamarvel.MarvelFragment
import es.santirivera.pruebamarvel.databinding.FragmentItemListBinding
import es.santirivera.pruebamarvel.list.adapter.MarvelCharacterAdapter
import es.santirivera.pruebamarvel.list.adapter.viewholder.MarvelCharacterViewHolder
import es.santirivera.pruebamarvel.list.state.CharacterListState
import es.santirivera.pruebamarvel.util.EndlessRecyclerViewScrollListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CharacterListFragment : MarvelFragment(),
    MarvelCharacterViewHolder.OnCharacterClickedCallback {

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    private val characterListViewModel: CharacterListViewModel by viewModels()

    private val adapter = MarvelCharacterAdapter(ArrayList<MarvelCharacter>(), this)

    private var lastState = CharacterListState()

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
        lifecycleScope.launch {
            characterListViewModel.uiState.collect { uiState ->
                if (uiState != lastState){
                    if (uiState.list != null) {
                        endLoad()
                        adapter.setNewValues(uiState.list!!)
                    } else if (uiState.error != null) {
                        val retry = handleErrors(uiState.error!!)
                        scrollListener.loadFailed()
                        if (retry) {
                            characterListViewModel.requestCharacters()
                        }
                    }
                    lastState = uiState
                }
            }
        }
        if (lastState.list.isNullOrEmpty()){
            startLoad()
        }
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
                    characterListViewModel.requestCharacters()
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