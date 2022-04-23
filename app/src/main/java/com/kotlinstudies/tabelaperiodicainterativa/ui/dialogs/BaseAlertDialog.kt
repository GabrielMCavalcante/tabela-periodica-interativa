package com.kotlinstudies.tabelaperiodicainterativa.ui.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.Window

/**
 * AlertDialog base para os outros dialogs do app
 *
 * Created by Gabriel Cavalcante - 06/04/2022
 */
open class BaseAlertDialog(context: Context) : AlertDialog(context, false, null) {
   override fun show() {
      requestWindowFeature(Window.FEATURE_NO_TITLE)
      window!!.setBackgroundDrawableResource(android.R.color.transparent)
      super.show()
   }
}