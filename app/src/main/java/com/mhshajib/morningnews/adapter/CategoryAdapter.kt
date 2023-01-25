package com.mhshajib.morningnews.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mhshajib.morningnews.fragment.category.tabs.BusinessFragment
import com.mhshajib.morningnews.fragment.category.tabs.EntertainmentFragment
import com.mhshajib.morningnews.models.Category

class CategoryAdapter(manager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(manager, lifecycle){
    companion object {
        val categoryList = listOf(
            Category(BusinessFragment(), "Business"),
            Category(EntertainmentFragment(), "Entertainment")
        )
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun createFragment(position: Int): Fragment {
        return categoryList[position].fragment
    }
}