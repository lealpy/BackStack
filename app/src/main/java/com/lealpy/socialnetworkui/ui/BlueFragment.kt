package com.lealpy.socialnetworkui.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lealpy.socialnetworkui.R
import com.lealpy.socialnetworkui.databinding.FragmentBlueBinding

class BlueFragment : Fragment(R.layout.fragment_blue) {

    private lateinit var binding : FragmentBlueBinding
    private var number = BLUE_FRAGMENT_NUMBER_START_VALUE

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBlueBinding.bind(view)

        if(savedInstanceState == null) {
            arguments?.getInt(BLUE_FRAGMENT_ARGUMENTS_NUMBER_KEY)?.let { number ->
                this.number = number
            }
        }
        else {
            number = savedInstanceState.getInt(BLUE_FRAGMENT_NUMBER_KEY, BLUE_FRAGMENT_NUMBER_START_VALUE)
        }

        initViews()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(BLUE_FRAGMENT_NUMBER_KEY, number)
    }

    private fun initViews() {
        binding.blueCounter.setOnClickListener {
            addFragment()
        }

        binding.blueCounter.text = number.toString()

    }

    private fun addFragment() {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.blueLayout, newInstance(number.inc()))
            .addToBackStack(BLUE_FRAGMENT_BACK_STACK)
            .commit()
    }

    companion object {
        const val BLUE_FRAGMENT_BACK_STACK = "BLUE_BACK_STACK"
        private const val BLUE_FRAGMENT_ARGUMENTS_NUMBER_KEY = "BLUE_FRAGMENT_ARGUMENTS_NUMBER_KEY"
        private const val BLUE_FRAGMENT_NUMBER_KEY = "BLUE_FRAGMENT_NUMBER_KEY"
        const val BLUE_FRAGMENT_NUMBER_START_VALUE = 0

        @JvmStatic
        fun newInstance(number: Int): BlueFragment {
            return BlueFragment().apply {
                arguments = Bundle().apply {
                    putInt(BLUE_FRAGMENT_ARGUMENTS_NUMBER_KEY, number)
                }
            }
        }
    }



}