package es.santirivera.pruebamarvel.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.pruebamarvel.databinding.ItemListContentBinding
import es.santirivera.pruebamarvel.list.adapter.viewholder.MarvelCharacterViewHolder

class MarvelCharacterAdapter(
    private val values: ArrayList<MarvelCharacter>,
    private val callback: MarvelCharacterViewHolder.OnCharacterClickedCallback
) :
    RecyclerView.Adapter<MarvelCharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharacterViewHolder {
        val binding =
            ItemListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarvelCharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarvelCharacterViewHolder, position: Int) {
        val item = values[position]
        holder.setElement(item, callback)
    }

    override fun getItemCount() = values.size

    fun setNewValues(it: List<MarvelCharacter>) {
        values.clear()
        values.addAll(it)
        notifyDataSetChanged()
    }

}