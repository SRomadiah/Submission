package com.example.submission.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    var username = ""
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowersFragment()

        fragment.arguments = Bundle().apply {
            putInt(FollowersFragment.ARG_POSITION, position+1)
            putString(FollowersFragment.ARG_USERNAME, username)
        }
        return fragment
    }

}