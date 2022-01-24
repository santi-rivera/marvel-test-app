package es.santirivera.pruebamarvel.list

import androidx.recyclerview.widget.RecyclerView
import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.pruebamarvel.R
import es.santirivera.pruebamarvel.databinding.ItemListContentBinding
import es.santirivera.pruebamarvel.util.loadUrl

class MarvelCharacterViewHolder(private val binding: ItemListContentBinding) :
    RecyclerView.ViewHolder(binding.root) {

    interface OnCharacterClickedCallback {
        fun onCharacterClicked(item: MarvelCharacter)
    }


    fun setElement(item: MarvelCharacter, callback: OnCharacterClickedCallback) {
        binding.textViewCharacterName.text = item.name
        binding.imageViewCharacter.loadUrl(R.drawable.loading_spinner, item.image)
        itemView.setOnClickListener {
            callback.onCharacterClicked(item)
        }
    }
}