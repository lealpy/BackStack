package com.lealpy.socialnetworkui.ui

import android.util.Log
import androidx.fragment.app.Fragment
import com.lealpy.socialnetworkui.R

class GreenFragment : Fragment(R.layout.fragment_green) {

    override fun onPause() {
        super.onPause()
        Log.d("MyLog", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLog", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLog", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MyLog", "onDetach")
    }

    companion object {
        const val GREEN_FRAGMENT_KEY = "GREEN_BACK_STACK"
    }

}