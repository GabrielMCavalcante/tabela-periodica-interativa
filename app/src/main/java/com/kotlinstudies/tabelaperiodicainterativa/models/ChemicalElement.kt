package com.kotlinstudies.tabelaperiodicainterativa.models

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.io.Serializable

data class ChemicalElement(
   val id: Int,
   val initials: String = "",
   val name: String = "",
   val atomicNumber: String = "-1",
   @DrawableRes val boxDrawable: Int = -1,
   @ColorRes val boxColor: Int = -1,
   val isIndex: Boolean = false
): Serializable

data class ChemicalElementDetails(
   val elementId: Int,
   @DrawableRes val image: Int,
   @StringRes val series: Int,
   val period: Int,
   val group: Int,
   val yearDiscovered: String,
   @StringRes val standardState: Int,
   @StringRes val description: Int,
   val electronicConfiguration: String,
   val atomicMass: String,
   val electronegativity: String,
   val atomicRadius: String,
   val ionizationEnergy: String,
   val electronAffinity: String,
   val oxidationStates: String,
   val density: String,
   val meltingPoint: String,
   val boilingPoint: String
): Serializable