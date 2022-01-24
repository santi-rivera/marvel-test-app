package es.santirivera.pruebamarvel.list

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.pruebamarvel.databinding.ItemListContentBinding

class MarvelCharacterViewHolder(private val binding: ItemListContentBinding) :
    RecyclerView.ViewHolder(binding.root) {

    interface OnCharacterClickedCallback {
        fun onCharacterClicked(item: MarvelCharacter)
    }


    fun setElement(item: MarvelCharacter, callback: OnCharacterClickedCallback) {
        binding.textViewCharacterName.text = item.name

        Glide.with(itemView.context)
            .load(item.image)
            .into(binding.imageViewCharacter)

        itemView.setOnClickListener {
            callback.onCharacterClicked(item)
        }
    }
}