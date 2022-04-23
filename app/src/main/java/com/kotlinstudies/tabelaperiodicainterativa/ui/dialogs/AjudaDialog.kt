package com.kotlinstudies.tabelaperiodicainterativa.ui.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import com.kotlinstudies.tabelaperiodicainterativa.R
import com.kotlinstudies.tabelaperiodicainterativa.databinding.DialogAjudaBinding

class AjudaDialog(ctx: Context): BaseAlertDialog(ctx) {
   private val binding = DialogAjudaBinding.inflate(LayoutInflater.from(context))

   init {
      binding.root.animation = AnimationUtils.loadAnimation(context, R.anim.zoom_in)
      setupDataBinding()
      setView(binding.root)
   }

   private fun setupDataBinding() {
      binding.ajudaDialog = this
   }
}