package es.santirivera.pruebamarvel.detail

import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.bold
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.pruebamarvel.databinding.FragmentItemDetailBinding
import android.content.Intent
import android.net.Uri


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
            binding.textViewCharacterName.text = it.name
            binding.textViewCharacterDescription.text = generateDescription(item!!)
            Glide.with(requireContext()).load(it.image).into(binding.imageViewCharacter)
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
            builder.bold { append("Character description:\n\n") }
            builder.append(item.description)
            builder.append("\n\n")
        }
        if (item.comicList.isNotEmpty()) {
            builder.bold { append("Appears in:\n") }
            for (comic in item.comicList) {
                builder.append("\n")
                builder.append(comic)
            }
        }
        return builder
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}