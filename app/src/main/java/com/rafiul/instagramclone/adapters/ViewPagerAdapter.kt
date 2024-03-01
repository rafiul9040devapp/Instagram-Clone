package com.rafiul.instagramclone.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rafiul.instagramclone.screens.fragments.MyPostFragment
import com.rafiul.instagramclone.screens.fragments.MyReelsFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return NUMBER_OF_TABS
    }

    override fun createFragment(position: Int): Fragment {
       return  when(position){
           0 -> MyPostFragment()
           1 -> MyReelsFragment()
           else -> MyPostFragment()
       }
    }

    companion object {
        private const val NUMBER_OF_TABS = 2
    }
}