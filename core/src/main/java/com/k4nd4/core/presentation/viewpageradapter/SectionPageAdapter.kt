package com.k4nd4.core.presentation.viewpageradapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPageAdapter(
    activity: AppCompatActivity
) : FragmentStateAdapter(activity) {

    private val fragmentList = arrayListOf<Fragment>()

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFragment(newFragmentList: List<Fragment>){
        fragmentList.addAll(newFragmentList)
    }
}