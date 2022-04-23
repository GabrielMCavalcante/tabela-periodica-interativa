package com.kotlinstudies.tabelaperiodicainterativa.ui.dialogs

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.animation.AnimationUtils
import com.kotlinstudies.tabelaperiodicainterativa.R
import com.kotlinstudies.tabelaperiodicainterativa.databinding.DialogImagemElementoBinding

class ImagemElementoDialog(ctx: Context, val elementImageDrawable: Drawable) : BaseAlertDialog(ctx) {
   private val binding = DialogImagemElementoBinding.inflate(layoutInflater)

   init {
      binding.root.animation = AnimationUtils.loadAnimation(context, R.anim.zoom_in)
      setupDataBinding()
      setView(binding.root)
   }

   private fun setupDataBinding() {
      binding.imagemElementoDialog = this
   }
}