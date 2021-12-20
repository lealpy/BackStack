package com.lealpy.socialnetworkui.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.lealpy.socialnetworkui.R
import com.lealpy.socialnetworkui.databinding.FragmentRedBinding

class RedFragment : Fragment(R.layout.fragment_red) {

    private lateinit var binding : FragmentRedBinding

    private val backStackListener = FragmentManager.OnBackStackChangedListener {
        binding.redCounter.text = parentFragmentManager.backStackEntryCount.toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRedBinding.bind(view)

        initViews()
        parentFragmentManager.addOnBackStackChangedListener(backStackListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        parentFragmentManager.removeOnBackStackChangedListener(backStackListener)
    }

    private fun initViews() {
        binding.redCounter.setOnClickListener {
            addFragment()
        }

        //Обновление при первом вызове (избавиться?)
        binding.redCounter.text = parentFragmentManager.backStackEntryCount.toString()

    }

    private fun addFragment() {
        parentFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, RedFragment::class.java, bundleOf())
            .setReorderingAllowed(true)
            .addToBackStack(RED_FRAGMENT_KEY)
            .commit()
    }

    companion object {
    const val RED_FRAGMENT_KEY = "RED_FRAGMENT_BACK_STACK"
    }
}
