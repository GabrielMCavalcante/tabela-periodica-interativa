package com.kotlinstudies.tabelaperiodicainterativa.ui.activities

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
   override fun onOptionsItemSelected(item: MenuItem): Boolean {
      if (item.itemId == android.R.id.home) {
         onBackPressed()
      }

      return super.onOptionsItemSelected(item)
   }
}