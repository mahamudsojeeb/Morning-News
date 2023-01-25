package com.mhshajib.morningnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mhshajib.morningnews.databinding.ActivityMainBinding
import com.mhshajib.morningnews.fragment.category.tabs.BookMarkFragment
import com.mhshajib.morningnews.fragment.category.tabs.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        bottom navigation
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.action_bookmark -> loadFragment(BookMarkFragment())
                else -> loadFragment(HomeFragment())
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.commit()
    }
}