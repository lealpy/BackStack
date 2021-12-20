package com.lealpy.socialnetworkui.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.lealpy.socialnetworkui.R
import com.lealpy.socialnetworkui.databinding.FragmentBlueBinding

class BlueFragment : Fragment(R.layout.fragment_blue) {

    private lateinit var binding : FragmentBlueBinding

    private val backStackListener = FragmentManager.OnBackStackChangedListener {
        binding.blueCounter.text = parentFragmentManager.backStackEntryCount.toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBlueBinding.bind(view)

        initViews()
        parentFragmentManager.addOnBackStackChangedListener(backStackListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        parentFragmentManager.removeOnBackStackChangedListener(backStackListener)
    }

    private fun initViews() {
        binding.blueCounter.setOnClickListener {
            addFragment()
        }

        //Обновление при первом вызове (избавиться?)
        binding.blueCounter.text = parentFragmentManager.backStackEntryCount.toString()

    }

    private fun addFragment() {
        parentFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, BlueFragment::class.java, bundleOf())
            .setReorderingAllowed(true)
            .addToBackStack(BLUE_FRAGMENT_KEY)
            .commit()
    }

    companion object {
        const val BLUE_FRAGMENT_KEY = "BLUE_BACK_STACK"
    }
}