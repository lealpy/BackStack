package com.lealpy.socialnetworkui.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.lealpy.socialnetworkui.R
import com.lealpy.socialnetworkui.databinding.FragmentRedBinding

class RedFragment : Fragment(R.layout.fragment_red) {

    private lateinit var binding : FragmentRedBinding
    private var number = RED_FRAGMENT_NUMBER_START_VALUE

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRedBinding.bind(view)

        if(savedInstanceState == null) {
            arguments?.getInt(RED_FRAGMENT_ARGUMENTS_NUMBER_KEY)?.let { number ->
                this.number = number
            }
        }
        else {
            number = savedInstanceState.getInt(RED_FRAGMENT_NUMBER_KEY, RED_FRAGMENT_NUMBER_START_VALUE)
        }

        initViews()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(RED_FRAGMENT_NUMBER_KEY, number)
    }

    private fun initViews() {
        binding.redCounter.setOnClickListener {
            addFragment()
        }

        binding.redCounter.text = number.toString()

    }

    private fun addFragment() {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.redLayout, newInstance(number.inc()))
            .addToBackStack(RED_FRAGMENT_BACK_STACK)
            .commit()
    }



    companion object {
        const val RED_FRAGMENT_BACK_STACK = "RED_FRAGMENT_BACK_STACK"
        private const val RED_FRAGMENT_ARGUMENTS_NUMBER_KEY = "RED_FRAGMENT_ARGUMENTS_NUMBER_KEY"
        private const val RED_FRAGMENT_NUMBER_KEY = "RED_FRAGMENT_NUMBER_KEY"
        const val RED_FRAGMENT_NUMBER_START_VALUE = 0


        @JvmStatic
        fun newInstance(number: Int): RedFragment {
            return RedFragment().apply {
                arguments = Bundle().apply {
                    putInt(RED_FRAGMENT_ARGUMENTS_NUMBER_KEY, number)
                }
            }
        }
    }

}
