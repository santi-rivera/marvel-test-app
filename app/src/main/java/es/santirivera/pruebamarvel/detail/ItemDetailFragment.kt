package es.santirivera.pruebamarvel.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.pruebamarvel.databinding.FragmentItemDetailBinding

@AndroidEntryPoint
class ItemDetailFragment : Fragment() {

    private var item: MarvelCharacter? = null

    private var _binding: FragmentItemDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root
        item = ItemDetailFragmentArgs.fromBundle(requireArguments()).character
        updateContent()
        return rootView
    }

    private fun updateContent() {
        item?.let {
            binding.textViewCharacterName?.text = it.name
            binding.textViewCharacterDescription?.text = it.description
            binding.imageViewCharacter?.let { it1 ->
                Glide.with(requireContext())
                    .load(it.image)
                    .into(it1)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}