package es.santirivera.pruebamarvel.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import dagger.hilt.android.AndroidEntryPoint
import es.santirivera.pruebamarvel.util.loadUrl
import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.pruebamarvel.MarvelFragment
import es.santirivera.pruebamarvel.R
import es.santirivera.pruebamarvel.databinding.FragmentItemDetailBinding


@AndroidEntryPoint
class ItemDetailFragment : MarvelFragment() {

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
            binding.textViewCharacterName.text = it.name
            binding.textViewCharacterDescription.text = generateDescription(item!!)
            binding.imageViewCharacter.loadUrl(R.drawable.loading_spinner, it.image)
            val wikiUrl = it.wikiUrl
            if (wikiUrl.isNotEmpty())
                binding.imageViewCharacter.setOnClickListener {
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(wikiUrl)
                    startActivity(i)
                }
        }
    }

    private fun generateDescription(item: MarvelCharacter): CharSequence {
        val builder = SpannableStringBuilder()
        if (item.description.isNotEmpty()) {
            builder.bold { append(getString(R.string.character_description)) }
            builder.append("\n\n")
            builder.append(item.description)
        }
        return builder
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}