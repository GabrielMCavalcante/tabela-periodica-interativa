package com.kotlinstudies.tabelaperiodicainterativa.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kotlinstudies.tabelaperiodicainterativa.R
import com.kotlinstudies.tabelaperiodicainterativa.databinding.ItemElementBinding
import com.kotlinstudies.tabelaperiodicainterativa.datasource.DataSource
import com.kotlinstudies.tabelaperiodicainterativa.models.ChemicalElement

class TabelaPeriodicaAdapter(
   private val clickHandler: (item: ChemicalElement) -> Unit
) : ListAdapter<ChemicalElement, RecyclerView.ViewHolder>(DiffCallback) {
   override fun getItemViewType(position: Int): Int {
      return when {
         DataSource.checkIsHalfSpaceIndex(position) -> VIEW_TYPE_HALF_SPACE
         DataSource.checkIsSpaceIndex(position) -> VIEW_TYPE_SPACE
         else -> VIEW_TYPE_ELEMENT
      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      return if (viewType == VIEW_TYPE_HALF_SPACE) {
         HalfSpaceViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_half_space,
            parent,
            false
         ))
      } else {
         ElementViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_element,
            parent,
            false
         ))
      }
   }

   override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
      val element = getItem(position)

      val root = holder.itemView.rootView

      root.background = null
      root.visibility = View.INVISIBLE
      root.isClickable = false

      if (holder.itemViewType == VIEW_TYPE_ELEMENT) {
         holder as ElementViewHolder
         holder.bind(element, clickHandler)
      }
   }

   companion object {
      private const val VIEW_TYPE_ELEMENT = 0
      private const val VIEW_TYPE_SPACE = 1
      private const val VIEW_TYPE_HALF_SPACE = 2

      class ElementViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
         private val binding = ItemElementBinding.bind(itemView)

         fun bind(chemicalElement: ChemicalElement, clickHandler: (item: ChemicalElement) -> Unit) {
            with(binding) {
               root.visibility = View.VISIBLE
               root.isClickable = true

               tvInicial.text = chemicalElement.initials
               tvNome.text = chemicalElement.name
               tvNumAtomico.text = chemicalElement.atomicNumber

               vBackground.setBackgroundResource(chemicalElement.boxDrawable)

               if (!chemicalElement.isIndex) {
                  root.setOnClickListener { clickHandler(chemicalElement) }
               }
            }
         }
      }

      class HalfSpaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

      val DiffCallback = object : DiffUtil.ItemCallback<ChemicalElement>() {
         override fun areItemsTheSame(
            oldItem: ChemicalElement,
            newItem: ChemicalElement
         ) = oldItem.id == newItem.id

         override fun areContentsTheSame(
            oldItem: ChemicalElement,
            newItem: ChemicalElement
         ) = oldItem == newItem
      }
   }
}