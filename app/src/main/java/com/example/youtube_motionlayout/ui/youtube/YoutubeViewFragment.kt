package com.example.youtube_motionlayout.ui.youtube

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.youtube_motionlayout.databinding.FragmentYoutubeViewBinding

class YoutubeViewFragment(
    val videoId: String
) : Fragment() {

    private var _binding: FragmentYoutubeViewBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentYoutubeViewBinding.inflate(inflater, container, false)

        return binding.root
    }



    fun closeFragment() {
        /*if (binding.motionLayout.currentState == R.id.start) {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(0, R.anim.slide_in_down)
                .remove(this@YoutubeViewFragment)
                .commit()
        } else {
            binding.motionLayout.transitionToStart()
        }*/
    }
}