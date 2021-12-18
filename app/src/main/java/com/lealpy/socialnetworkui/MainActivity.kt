package com.lealpy.socialnetworkui

import android.os.Bundle
import android.os.PersistableBundle
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
    private val fragments = listOf(redFragment, blueFragment, greenFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(savedInstanceState == null) {
            addFragmentsToStack()
        }
        else {

        }

        initViews()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

    }

    private fun initViews() {
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.redFragment -> {
                    showFragment(redFragment)
                }
                R.id.blueFragment -> {
                    showFragment(blueFragment)
                }
                R.id.greenFragment -> {
                    showFragment(greenFragment)
                }
            }
            true
        }
    }

    private fun increaseBottomNavViewLabel(menuItem : MenuItem) {
        binding.bottomNavView.getOrCreateBadge(menuItem.itemId).apply {
            number ++
            isVisible = true
        }
    }

    private fun setCurrentFragment(fragment : Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun showFragment(fragmentToShow: Fragment) {
        val fragmentsToHide = fragments.filter { frag ->
            frag != fragmentToShow
        }
        supportFragmentManager
            .beginTransaction()
            .hide(fragmentsToHide[0])
            .hide(fragmentsToHide[1])
            .show(fragmentToShow)
            .setPrimaryNavigationFragment(fragmentToShow)
            .commit()
    }

    private fun addFragmentsToStack() {
        supportFragmentManager
            .beginTransaction()
            .setReorderingAllowed(true)
            .add(R.id.fragmentContainer, blueFragment)
            .addToBackStack(BlueFragment.BLUE_FRAGMENT_BACK_STACK)
            .commit()

        supportFragmentManager
            .beginTransaction()
            .setReorderingAllowed(true)
            .add(R.id.fragmentContainer, greenFragment)
            .addToBackStack(GreenFragment.GREEN_BACK_STACK)
            .commit()

        supportFragmentManager
            .beginTransaction()
            .setReorderingAllowed(true)
            .add(R.id.fragmentContainer, redFragment)
            .addToBackStack(RedFragment.RED_FRAGMENT_BACK_STACK)
            .commit()


    }



}