package com.example.futuresomething.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.futuresomething.AnimationHelper
import com.example.futuresomething.R
import com.example.futuresomething.databinding.FragmentDetailsBinding
import com.example.futuresomething.databinding.FragmentWatchLaterBinding


class WatchLaterFragment : Fragment() {
    private lateinit var binding: FragmentWatchLaterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWatchLaterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(
            binding.watchLaterFragmentRoot,
            requireActivity(),
            1
        )
    }

}