package com.example.submission.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission.R
import com.example.submission.databinding.FragmentFollowersBinding
import java.sql.Struct


class FollowersFragment : Fragment() {


    private var _binding:FragmentFollowersBinding? = null
    private lateinit var viewModelFollowers: ViewModelFollowers
    private val binding get() = _binding!!
    private var position: Int? = null
    private var username: String? = null

    companion object {
        const val ARG_POSITION = "section number"
        const val ARG_USERNAME = "username"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModelFollowers = ViewModelProvider(requireActivity()).get(ViewModelFollowers::class.java)
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFollowersDetail.layoutManager = LinearLayoutManager(requireActivity())

        viewModelFollowers.listFollowers.observe(requireActivity()) {
            val adapter = FollowersAdapter()
            adapter.submitList(it)
            binding.rvFollowersDetail.adapter = adapter
        }

        viewModelFollowers.Loading.observe(requireActivity()) {
            showLoading(it)
        }

    }
    override fun onResume() {
        super.onResume()

        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME)
        }
        if (position == 1) {
            viewModelFollowers.findDataFollowers()
        } else {
            viewModelFollowers.findDataFollowing()
        }
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBarFollowers.visibility = View.VISIBLE
        } else {
            binding.progressBarFollowers.visibility = View.GONE
        }
    }
}


