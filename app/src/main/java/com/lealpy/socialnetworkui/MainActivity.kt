package com.lealpy.socialnetworkui

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.lealpy.socialnetworkui.databinding.ActivityMainBinding
import com.lealpy.socialnetworkui.ui.BlueFragment
import com.lealpy.socialnetworkui.ui.GreenFragment
import com.lealpy.socialnetworkui.ui.RedFragment

class MainActivity : FragmentActivity () {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val redFragment = RedFragment()
    private val blueFragment = BlueFragment()
    private val greenFragment = GreenFragment()

    private var currentFragmentKey = RedFragment.RED_FRAGMENT_KEY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(savedInstanceState == null) addFirstFragment()
        initViews()
        initBackStackListener()
    }

    private fun initViews() {
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.redFragment -> {
                    replaceFragment(redFragment)
                }
                R.id.blueFragment -> {
                    replaceFragment(blueFragment)
                }
                R.id.greenFragment -> {
                    replaceFragment(greenFragment)
                }
            }
            true
        }
    }

    private fun initBackStackListener() {
        supportFragmentManager.addOnBackStackChangedListener {
            //----------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        }
    }

    private fun replaceFragment(newFragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, newFragment)
            .setReorderingAllowed(true)
            .commit()

        supportFragmentManager.saveBackStack(currentFragmentKey)
        supportFragmentManager.restoreBackStack(getKeyByFragment(newFragment))

        currentFragmentKey = getKeyByFragment(newFragment)
    }

    private fun getFragmentByKey(key : String) : Fragment {
        return when(key) {
            BlueFragment.BLUE_FRAGMENT_KEY -> blueFragment
            GreenFragment.GREEN_FRAGMENT_KEY -> greenFragment
            else -> redFragment
        }
    }

    private fun getKeyByFragment(fragment : Fragment) : String {
        return when(fragment) {
            blueFragment -> BlueFragment.BLUE_FRAGMENT_KEY
            greenFragment -> GreenFragment.GREEN_FRAGMENT_KEY
            else -> RedFragment.RED_FRAGMENT_KEY
        }
    }

    private fun addFirstFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, getFragmentByKey(currentFragmentKey))
            .setReorderingAllowed(true)
            .commit()
    }

    private fun increaseBottomNavViewLabel(menuItem : MenuItem) {
        binding.bottomNavView.getOrCreateBadge(menuItem.itemId).apply {
            number ++
            isVisible = true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(CURRENT_FRAGMENT_KEY, currentFragmentKey)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentFragmentKey = savedInstanceState.getString(
            CURRENT_FRAGMENT_KEY,
            RedFragment.RED_FRAGMENT_KEY
        )
    }

    companion object {
        private const val CURRENT_FRAGMENT_KEY = "CURRENT_FRAGMENT_KEY"
    }

}