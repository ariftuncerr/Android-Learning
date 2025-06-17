package com.example.md3.tabLayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter (fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity){
    override fun createFragment(position: Int): Fragment {
        return when (position){
            0-> FirstFragment()
            1-> SecondFragment()
            else -> ThirdFragment()
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}