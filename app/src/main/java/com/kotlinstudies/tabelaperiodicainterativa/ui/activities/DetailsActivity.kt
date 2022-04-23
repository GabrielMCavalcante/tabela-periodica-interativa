package com.kotlinstudies.tabelaperiodicainterativa.ui.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.kotlinstudies.tabelaperiodicainterativa.databinding.ActivityDetailsBinding
import com.kotlinstudies.tabelaperiodicainterativa.datasource.DataSource
import com.kotlinstudies.tabelaperiodicainterativa.models.ChemicalElement
import com.kotlinstudies.tabelaperiodicainterativa.models.ChemicalElementDetails
import com.kotlinstudies.tabelaperiodicainterativa.ui.dialogs.ImagemElementoDialog

class DetailsActivity : BaseActivity() {
   private lateinit var binding: ActivityDetailsBinding

   private lateinit var chemicalElement: ChemicalElement
   private lateinit var chemicalElementDetails: ChemicalElementDetails

   private lateinit var chemicalElementDrawable: Drawable

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      with (window) {
         requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

         enterTransition = Slide(Gravity.BOTTOM)
         exitTransition = Slide(Gravity.TOP)
      }

      binding = ActivityDetailsBinding.inflate(layoutInflater)

      setContentView(binding.root)

      setupToolbar()

      chemicalElement = intent.extras!!.getSerializable(EXTRA_CHEMICAL_ELEMENT) as ChemicalElement
      chemicalElementDetails = DataSource.detailsDataSource.first { d -> d.elementId == chemicalElement.id }

      setupDataBinding()
   }

   @SuppressLint("RestrictedApi")
   private fun setupToolbar() {
      setSupportActionBar(binding.toolbar)
      supportActionBar?.apply {
         title = ""
         setDisplayHomeAsUpEnabled(true)
         setDefaultDisplayHomeAsUpEnabled(true)
      }
   }

   private fun setupDataBinding() {
      val activity = this

      chemicalElementDrawable = ResourcesCompat.getDrawable(
         resources,
         activity.chemicalElementDetails.image,
         null
      )!!

      binding.apply {
         lifecycleOwner = activity

         detailsActivity = activity

         elementImageDrawable = chemicalElementDrawable

         elementSeriesBgColor = ContextCompat.getColor(activity, activity.chemicalElement.boxColor)

         chemicalElement = activity.chemicalElement
         chemicalElementDetails = activity.chemicalElementDetails
      }
   }

   fun onElementImageClick() {
      ImagemElementoDialog(this, chemicalElementDrawable).show()
   }

   companion object {
      private const val EXTRA_CHEMICAL_ELEMENT = "EXTRA_CHEMICAL_ELEMENT"

      @JvmStatic
      fun launchWith(context: Context, chemicalElement: ChemicalElement) {
         context.startActivity(Intent(context, DetailsActivity::class.java).apply {
            putExtra(EXTRA_CHEMICAL_ELEMENT, chemicalElement)
         })
      }
   }
}