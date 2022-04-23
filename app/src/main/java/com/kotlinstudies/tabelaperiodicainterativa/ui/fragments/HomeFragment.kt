package com.kotlinstudies.tabelaperiodicainterativa.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kotlinstudies.tabelaperiodicainterativa.databinding.FragmentHomeBinding
import com.kotlinstudies.tabelaperiodicainterativa.datasource.DataSource
import com.kotlinstudies.tabelaperiodicainterativa.ui.activities.DetailsActivity
import com.kotlinstudies.tabelaperiodicainterativa.ui.adapters.TabelaPeriodicaAdapter
import com.kotlinstudies.tabelaperiodicainterativa.ui.dialogs.AjudaDialog

class HomeFragment : Fragment() {
   private var _binding: FragmentHomeBinding? = null
   private val binding get() = _binding!!

   private val dataSource = DataSource.dataSource

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentHomeBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      setupDataBinding()

      binding.rvTabela.setHasFixedSize(true)

      val adapter = TabelaPeriodicaAdapter { selectedElement ->
         DetailsActivity.launchWith(requireContext(), selectedElement)
      }

      binding.rvTabela.adapter = adapter
      adapter.submitList(dataSource)
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }

   private fun setupDataBinding() {
      binding.apply {
         lifecycleOwner = viewLifecycleOwner
         homeFragment = this@HomeFragment
      }
   }

   fun onAjudaClick() {
      AjudaDialog(requireContext()).show()
   }
}